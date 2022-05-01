package kito.lab5.client.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Класс описывающий объект координаты
 */
public class Coordinates {

    @Max(61)
    private float x; //Значение поля должно быть больше -759
    private @NotNull Float y;

    /**
     * @return координата по X
     */
    public float getX() {
        return this.x;
    }

    /**
     * Метод, позволяющий задать координату по X
     * @param x значение координаты по X в строковом формате
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return координата по Y
     */
    public @NotNull Float getY() {
        return this.y;
    }

    /**
     * Метод, позволяющий задать координату по Y
     * @param y значение координаты по X
     */
    public void setY(Float y) {
        this.y = y;
    }
}
