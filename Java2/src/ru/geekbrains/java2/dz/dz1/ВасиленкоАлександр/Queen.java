package ru.geekbrains.java2.dz.dz1.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class Queen implements ShapeChess {
    @Override
    public boolean isRightMove(String from, String to) {
        int diffNumbers = ChessBoard.getInstance().getDifferenceBetweenTwoNumbers(from, to);
        int diffLetters = ChessBoard.getInstance().getDifferenceBetweenTwoLetters(from, to);
        return (Math.abs(diffLetters) == Math.abs(diffNumbers))     //если движение по диагонали
                || (diffLetters == 0)                               //если вдижение вверх или вниз
                || (diffNumbers == 0);                              //если вдижение ввлево или ввправо
    }
}
