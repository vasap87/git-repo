package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int typeGame;

    public static void main(String[] args) {
        //Меню
        Menu menu = new Menu();
        menu.setMenuItem();
        if(menu.getMenuItem()==1){
            System.out.println("Введите ваше имя:");
            String nameUser = sc.next();
            Player p1 = new HumanPlayer();
            p1.setName(nameUser);
            Player p2 = new AIPlayer();
            game(p1,p2);
        }else if(menu.getMenuItem()==2){
            //первый игрок
            System.out.println("Введите имя первого игрока:");
            String nameUser1 = sc.next();
            Player p1 = new HumanPlayer();
            p1.setName(nameUser1);
            //второй игрок
            System.out.println("Введите имя второго игрока:");
            String nameUser2 = sc.next();
            Player p2 = new HumanPlayer();
            p1.setName(nameUser2);
            //игра
            game(p1,p2);
        }else if(menu.getMenuItem()==3){
            Player p1 = new AIPlayer();
            Player p2 = new AIPlayer();
            game(p1,p2);
        }


    }
    //Метод для игры
    private static void game(Player p1, Player p2){
        p1.getGameField().printField(true);
        while(true){
            p1.turn(p2.getGameField());
            p2.getGameField().printField(true);
            if(p2.getGameField().isDefeated()) break;
            p2.turn(p1.getGameField());
            p1.getGameField().printField(true);
            if(p1.getGameField().isDefeated()) break;
        }

    }
}
