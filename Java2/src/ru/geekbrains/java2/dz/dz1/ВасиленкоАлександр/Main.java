package ru.geekbrains.java2.dz.dz1.ВасиленкоАлександр;


import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //выводим для информации названия фигур
        System.out.println(ChessBoard.getInstance().printCheesShapeNames());
        System.out.println("Введите символ фигуры:");
        //читаем название
        String shape = sc.next();
        String from = new String();
        String to = new String();
        //пока введённые координаты не будут удовлетворять размеры поля
        do {
            System.out.println("Введите координаты откуда и куда ходит фигура(a-h 1-8)");
            from = sc.next();
            to = sc.next();
        } while (!ChessBoard.getInstance().isLegalCoordinates(from, to));

        char shapeChar = shape.charAt(0);
        //создаём объект
        ShapeChess p = FactoryShapeChees.getInstance().factoryMethod(shapeChar);
        //выводим результат
        System.out.println("Ход "+ shapeChar + " из " + from + " до " + to + (p.isRightMove(from, to)?" возможен":" не возможен"));

    }
}
