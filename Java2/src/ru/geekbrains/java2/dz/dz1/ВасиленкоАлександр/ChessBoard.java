package ru.geekbrains.java2.dz.dz1.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 * Вспомогательный класс
 */
public class ChessBoard {
    //единственный экземпляр класса
    private static ChessBoard instance = new ChessBoard();;

    //метод доступа к единственному экземпляру класса
    public static ChessBoard getInstance() {
        return instance;
    }

    //закрытый конструктор
    private ChessBoard() {

    }

    //Расчёт разницы между буквенными обозначениями координат
    public int getDifferenceBetweenTwoLetters(String from, String to) {
        char fromChar = from.charAt(0);
        char toChar = to.charAt(0);
        return toChar - fromChar;
    }

    //Расчёт разницы между цифровыми обозначениями координат
    public int getDifferenceBetweenTwoNumbers(String from, String to) {
        int fromInt = Integer.parseInt(from.substring(1));
        int toInt = Integer.parseInt(to.substring(1));
        return toInt - fromInt;
    }

    //Координаты хода находятся в пределах игрового поля
    public boolean isLegalCoordinates(String from, String to) {
        char fromChar = from.charAt(0);
        char toChar = to.charAt(0);
        int fromInt = Integer.parseInt(from.substring(1));
        int toInt = Integer.parseInt(to.substring(1));

        return fromChar >= 'a' && fromChar <= 'h' && toChar >= 'a' && toChar <= 'h'
                && fromInt >= 1 && fromInt <= 8 && toInt >= 1 && toInt <= 8;
    }

    //Вывод названий шахматныъх фигур
    public String printCheesShapeNames(){
        return "В шахматах есть следующие фигуры и их обозначения: \n"+
                "p - Pawn - Пешка\n"+
                "k - King - Король\n"+
                "q - Queen - Ферзь\n"+
                "r - Rook - Ладья\n"+
                "n - kNight - Конь\n"+
                "b - Bishop - Слон\n"+
                "(c) - wikipedia";
    }


}
