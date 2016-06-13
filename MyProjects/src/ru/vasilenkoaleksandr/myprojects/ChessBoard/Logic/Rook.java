package ru.vasilenkoaleksandr.myprojects.ChessBoard.Logic;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class Rook implements ShapeChess {
    @Override
    public boolean isRightMove(String from, String to) {
        int diffNumbers = ChessBoard.getInstance().getDifferenceBetweenTwoNumbers(from, to);
        int diffLetters = ChessBoard.getInstance().getDifferenceBetweenTwoLetters(from, to);
        return  (diffLetters == 0)      //если вдижение вверх или вниз
                || (diffNumbers == 0);  //если вдижение ввлево или ввправо;
    }
}
