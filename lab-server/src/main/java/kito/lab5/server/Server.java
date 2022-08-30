package kito.lab5.server;

import kito.lab5.server.utils.TextSender;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public final class Server {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket s;

    public static void main(String[] args) {
        Server server = new Server();
        TextSender.changePrintStream(server.dataOutputStream);
        Application application = new Application(server.dataInputStream);
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
            dataInputStream = new DataInputStream(s.getInputStream());
            dataOutputStream=new DataOutputStream(s.getOutputStream());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
