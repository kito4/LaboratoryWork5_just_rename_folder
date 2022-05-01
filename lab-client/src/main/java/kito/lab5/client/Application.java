package kito.lab5.client;

import kito.lab5.client.csv_parser.CSVReader;
import kito.lab5.client.user_command_line.CommandListener;
import kito.lab5.client.utils.TextSender;

import java.io.FileNotFoundException;

public class Application {

    CSVReader collectionFileReader;
    CommandListener commandListener;

    public Application() {
        collectionFileReader = new CSVReader();
        commandListener = new CommandListener(System.in);
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
