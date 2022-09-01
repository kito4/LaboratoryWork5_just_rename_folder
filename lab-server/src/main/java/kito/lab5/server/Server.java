package kito.lab5.server;

import kito.lab5.server.utils.TextSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public final class Server {
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket s;

    public static void main(String[] args) {
        Server server = new Server();
        TextSender.changePrintStream(server.objectOutputStream);
        Application application = new Application(server.objectInputStream);
        System.out.println(System.getenv("HUMAN_INFO"));
        application.launchApplication();
    }
    public Server(){
        setUpConnection();
    }
    public void setUpConnection(){
        ServerSocket ss=null;
        try {
            ss = new ServerSocket(4550);
            s= ss.accept();
            objectInputStream = new ObjectInputStream(s.getInputStream());
            objectOutputStream=new ObjectOutputStream(s.getOutputStream());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
