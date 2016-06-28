package ru.geekbrains.java2.dz.dz1.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class Pawn implements ShapeChess {
    @Override
    public boolean isRightMove(String from, String to) {
        int diffNumbers = ChessBoard.getInstance().getDifferenceBetweenTwoNumbers(from, to);
        int diffLetters = ChessBoard.getInstance().getDifferenceBetweenTwoLetters(from, to);
        int fromInt = Integer.parseInt(from.substring(1));
        return (diffNumbers == 1 && Math.abs(diffLetters) == 1) //если вдижение вперёд по диагонали
                || (diffLetters == 0 && diffNumbers == 1)       //если движение вперёд
                || (fromInt == 2 && diffNumbers == 2);          //если первоначальная позиция во втором ряду
    }

}
