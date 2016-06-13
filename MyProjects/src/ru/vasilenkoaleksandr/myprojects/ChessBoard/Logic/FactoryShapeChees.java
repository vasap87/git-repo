package ru.vasilenkoaleksandr.myprojects.ChessBoard.Logic;


/**
 * Created by vasilenko.aleksandr on 07.06.2016.
 * Фабричный метод создания определённого объекта.
 */
public class FactoryShapeChees {

    private static FactoryShapeChees ourInstance = new FactoryShapeChees();

    public static FactoryShapeChees getInstance() {
        return ourInstance;
    }

    private FactoryShapeChees() {
    }

    public ShapeChess factoryMethod (char ch) throws ChessNotFoundExeption {
        ShapeChess shapeChess = null;
        switch (ch){
            case 'p': shapeChess = new Pawn(); break;   //пешка
            case 'k': shapeChess = new King(); break;   //король
            case 'q': shapeChess = new Queen(); break;  //ферзь
            case 'r': shapeChess = new Rook(); break;   //ладья
            case 'n': shapeChess = new KNight(); break; //конь
            case 'b': shapeChess = new Bishop(); break; //слон
            default: throw new ChessNotFoundExeption();
        }
        return shapeChess;
    }
}
