package kito.lab5.server;

import kito.lab5.server.csv_parser.CSVReader;
import kito.lab5.server.user_command_line.CommandListener;
import kito.lab5.server.utils.TextSender;

import java.io.DataInputStream;
import java.io.FileNotFoundException;

public class Application {

    CSVReader collectionFileReader;
    CommandListener commandListener;

    public Application(DataInputStream dis) {
        collectionFileReader = new CSVReader();
        commandListener = new CommandListener(dis);
    }

    public void launchApplication() {
        try {
            collectionFileReader.initializeFile(Config.getFilePath());
            collectionFileReader.parseFile();
            Config.getCollectionManager().fillWithArray(collectionFileReader.getInfoFromFile());
            commandListener.readCommands();
        } catch (FileNotFoundException e) {
            TextSender.printError("Файл: " + Config.getFilePath() + " не найден");
        } catch (NullPointerException e) {
            TextSender.printError("Пожалуйста проинциализируйте системную переменную HUMAN_INFO, " +
                    "содержащую путь до файла с информацией о коллекции");
        }
    }
}
