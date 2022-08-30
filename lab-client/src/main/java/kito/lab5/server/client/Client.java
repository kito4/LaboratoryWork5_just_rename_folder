package kito.lab5.server.client;



import kito.lab5.server.exceptions.EndOfFileException;
import kito.lab5.server.utils.*;
import kito.lab5.common.util.Request;
import kito.lab5.common.util.Response;
import kito.lab5.common.util.Serializer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }




    public static void main(String[] args) throws IOException, ClassNotFoundException, EndOfFileException {
        Scanner scanner = new Scanner(System.in);
        HumanFactory humanFactory = new HumanFactory(new ScannerFieldsGetter(scanner), new ReaderFieldsGetter(null));
        Selector selector = Selector.open();
        SocketAddress clientAddress = new InetSocketAddress(InetAddress.getLocalHost(), 4550);
        SocketChannel clientChannel = SocketChannel.open(clientAddress);
        System.out.println("Connected!");
        System.out.println("введите help чтобы увидеть список допустимых команд.");
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_WRITE);

        UserInputHandler userInputHandler = new UserInputHandler();

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {
                    SocketChannel serverChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(4096);
                    serverChannel.read(buffer);
                    Response response = Serializer.deSerializeResponse(buffer.array());
                    ResponseHandler.handleResponse(response);
                    if (response.isObjectNeeded()) {
                        Request request = new Request();
                        request.setHuman(humanFactory.start(true));// Что нужно передать в объекте только строку или всю коллекцию(HumanBeing)?
                        try {
                            ByteBuffer bufferToSend = Serializer.serializeRequest(request);
                            clientChannel.write(bufferToSend);
                            bufferToSend.clear();
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                } else if (key.isWritable()) {

                    Request request = new Request();
                    request.setCommandNameAndArguments(userInputHandler.getCommand());
                    try {
                        ByteBuffer buffer = Serializer.serializeRequest(request);
                        buffer.clear();
                        clientChannel.write(buffer);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
