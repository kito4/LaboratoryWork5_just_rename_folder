package kito.lab5.server.utils;

import kito.lab5.server.abstractions.AbstractMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class TextSender {

    public static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN
    public static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED
    public static final String ANSI_RESET = "\u001B[0m";
    public static PrintStream printStream = System.out;
    public static DataOutputStream dos;
    public static void printText(String message) {
        try {
            dos.writeUTF(MESSAGE_COLOR + message + ANSI_RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printError(String message) {
        try {
            dos.writeUTF(ERROR_COLOR + message + ANSI_RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printMessage(AbstractMessage message) {
        try {
            dos.writeUTF(message.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changePrintStream(DataOutputStream newPrintStream) {
        dos = newPrintStream;
    }
}
