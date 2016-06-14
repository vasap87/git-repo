package ru.vasilenkoaleksandr.myprojects.ChessBoard.graffic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by admin on 13.06.2016.
 */
public class ChessField extends JTextField {
    private int positionX;
    private int positionY;
    private String label = new String();

    public ChessField( int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
        setHorizontalAlignment(CENTER);
        setEnabled(false);
        setFocusable(true);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println((char)getPositionX()+""+getPositionY());
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

    public void setColor(Color c){
        setBackground(c);
    }

    public void clearLabel(){
        setText("");
    }

    public void setChess(String s){
        setText(s);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public char getChess(){
        return getText().charAt(0);
    }
}
