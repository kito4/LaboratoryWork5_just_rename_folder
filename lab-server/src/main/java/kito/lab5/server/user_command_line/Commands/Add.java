package kito.lab5.server.user_command_line.Commands;

import kito.lab5.server.Config;
import kito.lab5.server.abstractions.AbstractCommand;
import kito.lab5.server.user_command_line.ErrorMessage;
import kito.lab5.server.user_command_line.HumanInfoInput;
import kito.lab5.server.user_command_line.SuccessMessage;

public class Add extends AbstractCommand {

    public Add() {
        super("add", "Добавить элемент в коллекцию, принимает на вход [Имя, наличие героизма(true/false), наличие зубочистки(true/false), скорость удара,ожидание]", 5);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                HumanInfoInput humanInfoInput = new HumanInfoInput(args);
                humanInfoInput.inputHuman();
                Config.getCollectionManager().addHuman(humanInfoInput.getNewHumanToInput());
                return new SuccessMessage("Объект успешно добавлен в коллекцию");
            } catch (IllegalArgumentException e) {
                return new ErrorMessage(e.getMessage());
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
