package kito.lab5.server;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;


import java.nio.channels.SocketChannel;
import java.util.Scanner;


public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try {
            //ServerSocketChannel
            boolean exit = false;
            SocketChannel sock = SocketChannel.open(new InetSocketAddress("localhost", 4550));
            ByteBuffer buffer = ByteBuffer.allocate(65536);
            Scanner scanner = new Scanner(System.in);
            String msg;

            sock.read(buffer);


            System.out.println(new String(buffer.array()));
            while (true) {
                msg = scanner.nextLine();
                if (msg == scanner.nextLine()) {
                    break;
                }
                buffer = ByteBuffer.wrap(scanner.nextLine().getBytes());
                sock.write(buffer);
                buffer.clear();
                sock.read(buffer);
                System.out.println(new String(buffer.array()));



            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}