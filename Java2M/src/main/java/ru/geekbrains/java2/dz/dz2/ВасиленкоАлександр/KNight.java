package ru.geekbrains.java2.dz.dz2.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class KNight implements ShapeChess {
    @Override
    public boolean isRightMove(String from, String to) {
        int diffNumbers = ChessBoard.getInstance().getDifferenceBetweenTwoNumbers(from, to);
        int diffLetters = ChessBoard.getInstance().getDifferenceBetweenTwoLetters(from, to);
        return (Math.abs(diffNumbers) == 2 && Math.abs(diffLetters) == 1)
                || (Math.abs(diffNumbers) == 1 && Math.abs(diffLetters) == 2);
    }
}
