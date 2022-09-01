package kito.lab5.server.user_command_line;

import kito.lab5.common.util.Request;
import kito.lab5.server.Config;
import kito.lab5.server.abstractions.AbstractMessage;
import kito.lab5.server.utils.SmartSplitter;
import kito.lab5.server.utils.TextSender;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс отвечающий за работу с пользователем в интерактивном режиме
 */
public class CommandListener {

    private boolean isRunning;
    private final ObjectInputStream commandsInputStream;

    /**
     * Конструктор
     */
    public CommandListener(ObjectInputStream inputStream) {
        TextSender.printText("Добро пожаловать в интерактивный режим работы с коллекцией, " +
                "введите help, чтобы узнать информацию о доступных командах");
        commandsInputStream = inputStream;
    }

    /**
     * Метод, читающий команды  до тех пор, пока не возникнет команда exit
     */
    public void readCommands() {
        isRunning = true;
        Scanner scanner = new Scanner(commandsInputStream);
        while (isRunning) {
            try {
                Request request = (Request)commandsInputStream.readObject();
                String line =  request.getCommandNameAndArguments();
                //if ("exit".equals(line)) {
                //    isRunning = false;
                //    continue;
                //}
                String[] inputString = SmartSplitter.smartSplit(line).toArray(new String[0]);
                String commandName = inputString[0].toLowerCase();
                String[] commandArgs = Arrays.copyOfRange(inputString, 1, inputString.length);
                TextSender.printMessage(((AbstractMessage) Config.getCommandManager().execute(commandName.toLowerCase(), commandArgs)).getMessage());
            } catch (NoSuchElementException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        scanner.close();
    }

}
