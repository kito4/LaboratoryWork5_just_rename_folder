package kito.lab5.server.utils;


import kito.lab5.server.exceptions.EndOfFileException;
import kito.lab5.common.entities.Coordinates;
import kito.lab5.common.entities.HumanBeing;



import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * class for the getting the fields
 */

public class HumanFactory {

    /**
     * terminal input
     */
    private FieldsGetterInterface scanner;

    /**
     * file input
     */
    private FieldsGetterInterface reader;

    private Scanner sc = new Scanner(System.in);

    public HumanFactory(FieldsGetterInterface scanner, FieldsGetterInterface reader) {
        this.scanner = scanner;
        this.reader = reader;
    }

    public HumanBeing start(boolean isInteractive) throws EndOfFileException {
        if (isInteractive) {
            HumanBeing human = new HumanBeing(true);
            System.out.println("Введите координаты x (Float) y (int) ");
            Coordinates t1=new Coordinates();
            t1.setX(Float.parseFloat(sc.nextLine()));
            t1.setY(sc.nextInt());
            human.setCoordinates(t1);
            human.setCreationDate(ZonedDateTime.parse(ZonedDateTime.now().toString()));
            return human;
        } else {

            HumanBeing human = new HumanBeing(true);
            human.setId();
            human.setCoordinates(reader.getCoordinates());
            Coordinates t1=new Coordinates();
            t1.setX(Float.parseFloat(sc.nextLine()));
            t1.setY(sc.nextInt());
            human.setCoordinates(t1);
            human.setCreationDate(ZonedDateTime.parse(ZonedDateTime.now().toString()));

            return human;
        }


    }

    public FieldsGetterInterface getScanner() {
        return scanner;
    }
    public FieldsGetterInterface getReader() {return reader;}

}
