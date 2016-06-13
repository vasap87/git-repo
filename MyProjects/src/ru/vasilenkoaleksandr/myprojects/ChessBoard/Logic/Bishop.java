package ru.vasilenkoaleksandr.myprojects.ChessBoard.Logic;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class Bishop implements ShapeChess {
    @Override
    public boolean isRightMove(String from, String to) {
        int diffNumbers = ChessBoard.getInstance().getDifferenceBetweenTwoNumbers(from, to);
        int diffLetters = ChessBoard.getInstance().getDifferenceBetweenTwoLetters(from, to);
        return (Math.abs(diffLetters) == Math.abs(diffNumbers));     //если движение по диагонали;
    }
}
