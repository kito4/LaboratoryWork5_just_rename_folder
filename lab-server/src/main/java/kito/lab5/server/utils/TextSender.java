package kito.lab5.server.utils;

import kito.lab5.common.util.Response;

import java.io.*;

public class TextSender {

    public static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN
    public static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED
    public static final String ANSI_RESET = "\u001B[0m";
    public static PrintStream printStream = System.out;
    public static ObjectOutputStream oos;
    public static void printText(String text) {
        try {
            Response response = new Response();
            response.setMessage(text);
            oos.writeObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printError(String text) {
        try {
            Response response = new Response();
            response.setMessage(text);
            oos.writeObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printMessage(String text) {
        try {
            Response response = new Response();
            response.setMessage(text);
            oos.writeObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changePrintStream(ObjectOutputStream newPrintStream) {
        oos = newPrintStream;
    }
}
