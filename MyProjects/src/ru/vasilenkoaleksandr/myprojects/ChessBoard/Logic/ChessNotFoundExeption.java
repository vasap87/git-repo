package ru.vasilenkoaleksandr.myprojects.ChessBoard.Logic;

/**
 * Created by admin on 13.06.2016.
 */
public class ChessNotFoundExeption extends ChessExeption {

    @Override
    public String toString() {
        return "Название фигуры задано некорректно";
    }
}
