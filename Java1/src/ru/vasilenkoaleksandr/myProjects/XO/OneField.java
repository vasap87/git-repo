package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 02.06.2016.
 */
public class OneField extends JTextField {
    private Random random = new Random();

    public OneField(int x ,int y, char ch, JField jField){

        Font font = new Font("Arial",Font.PLAIN,16);
        setFont(font);
        setHorizontalAlignment(CENTER);
        setPreferredSize(new Dimension(30,30));
        setText(""+ch);
        setEditable(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //ход игрока
                GameField.getInstance().turn(x,y,'X');
                //обновление JField
                JField.getInstance().rePrintField();
                //ход компа
                int xAi, yAI;
                do{
                    xAi=random.nextInt(5);
                    yAI=random.nextInt(5);
                }while(!GameField.getInstance().turn(xAi,yAI,'O'));
                //обновление JField
                JField.getInstance().rePrintField();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


}
