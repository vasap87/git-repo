package ru.vasilenkoaleksandr.myProjects.Experiments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 28.05.2016.
 */
public class Grafic extends JFrame {
    private JLabel label;

    public Grafic(){
        //Контейнер
        JFrame jFrame = new JFrame("АЕ! (:");

        //Размер
        jFrame.setSize(300,130);

        //Где расположить
        jFrame.setLocation(1600,300);

        //Когда пользователь закрывает окно программа завершает исполнения
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Диспетчер компоновки FlowLayout
        jFrame.setLayout(new FlowLayout());

        //Первоначальное значение для метки
        label = new JLabel();
        label.setText("АЕ - щастливая семья!");

        //Две кнопки
        JButton buttonAgree = new JButton("Сашка!");
        JButton buttonNoAgree = new JButton("Женька!");

        //Обработчик для первой
        buttonAgree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Сашка любит Женьку!!!");
            }
        });

        //Обработчик для второй
        buttonNoAgree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Женька любит Сашку!!");
            }
        });

        jFrame.add(buttonAgree);
        jFrame.add(buttonNoAgree);
        jFrame.add(label);
        jFrame.setVisible(true);
    }
}
