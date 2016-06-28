package ru.geekbrains.java2.dz.dz1.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class King implements ShapeChess {
    @Override
    public boolean isRightMove(String from, String to) {
        int diffNumbers = ChessBoard.getInstance().getDifferenceBetweenTwoNumbers(from, to);
        int diffLetters = ChessBoard.getInstance().getDifferenceBetweenTwoLetters(from, to);
        return (Math.abs(diffLetters) == 1 && Math.abs(diffNumbers) == 1)   //на одну клетку по диагонали
                || (diffLetters == 0 && Math.abs(diffNumbers) == 1)         //на 1 клетку вниз или вверх
                || (Math.abs(diffLetters) == 1 && diffNumbers == 0);        //на 1 клетку влево или вправо
    }
}
