package ru.vasilenkoaleksandr.myProjects.XO;

/**
 * Created by admin on 05.06.2016.
 */
public class WinCoordinates {
    private int x;
    private int y;

    public WinCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "WinCoordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
