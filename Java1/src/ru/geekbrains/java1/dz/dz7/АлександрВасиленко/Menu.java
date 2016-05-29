package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

import javax.security.sasl.SaslClient;
import java.util.Scanner;

/**
 * Created by admin on 29.05.2016.
 */
public class Menu {
    private int menuItem;
    private Scanner sc;
    public Menu(){
        menuItem = 0;
    }

    public int getMenuItem() {
        return menuItem;
    }

    public void setMenuItem() {
        sc = new Scanner(System.in);
        System.out.println("Игровое меню:\n Игра с компьютером: введите 1; " +
                                        "\n Игра с человеком: введите 2; " +
                                        "\n Игра компьютера с компьютером: введите 3;");
        this.menuItem = sc.nextInt();
        sc.close();
    }
}
