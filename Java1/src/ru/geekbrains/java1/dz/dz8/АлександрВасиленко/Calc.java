package ru.geekbrains.java1.dz.dz8.АлександрВасиленко;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vasilenko.aleksandr on 31.05.2016.
 */
public class Calc extends JFrame {
    double before, after;
    char oper = 0;

    public Calc() {
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new BoxLayout(upPanel,BoxLayout.LINE_AXIS));

        //добавление поля для ввода-вывода
        JTextField label = new JTextField();
        label.setEditable(false);
        upPanel.add(label);

        //кнопка стереть всё
        JButton clearButton = new JButton("Clear");
        upPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                before=0;
                after=0;
            }
        });
        add(upPanel,BorderLayout.NORTH);

        //панель с кнопками
        JPanel butJPanel = new JPanel();
        butJPanel.setLayout(new GridLayout(4, 4));
        //массив с цифрами
        NumButtons[] arrJBNum = new NumButtons[10];
        for (int i = 0; i < arrJBNum.length; i++) {
            arrJBNum[i] = new NumButtons("" + i, label);
        }
        //массив с операциями
        JButton[] arrJBOper = new OperButtons[5];
        arrJBOper[0] = new OperButtons("+", '+', label, this);
        arrJBOper[1] = new OperButtons("-", '-', label, this);
        arrJBOper[2] = new OperButtons("/", '/', label, this);
        arrJBOper[3] = new OperButtons("*", '*', label, this);
        arrJBOper[4] = new OperButtons("%", '%', label, this);

        //кнопка равно
        JButton res = new JButton("=");
        res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder last = new StringBuilder(label.getText());
                after = getLastNum(last);
                setLabelResult(before, after, oper, label);
                before = 0;
                after = 0;

            }
        });

        butJPanel.add(arrJBNum[7]);
        butJPanel.add(arrJBNum[8]);
        butJPanel.add(arrJBNum[9]);
        butJPanel.add(arrJBOper[0]);
        butJPanel.add(arrJBNum[4]);
        butJPanel.add(arrJBNum[5]);
        butJPanel.add(arrJBNum[6]);
        butJPanel.add(arrJBOper[1]);
        butJPanel.add(arrJBNum[1]);
        butJPanel.add(arrJBNum[2]);
        butJPanel.add(arrJBNum[3]);
        butJPanel.add(arrJBOper[2]);
        butJPanel.add(arrJBNum[0]);
        butJPanel.add(res);
        butJPanel.add(arrJBOper[4]);
        butJPanel.add(arrJBOper[3]);
        add(butJPanel, BorderLayout.CENTER);
        setVisible(true);


    }

    //возвращает последние введённые символы
    private double getLastNum(StringBuilder last) {
        StringBuilder res = new StringBuilder();
        for (int i = last.length() - 1; i >= 0; i--) {
            if (Character.isDigit(last.charAt(i))) {
                res.append(last.charAt(i));
            } else break;
        }
        res.reverse();
        return Double.parseDouble(res.toString());
    }

    //обрезаем нули в лейбле
    private void cutLabel(JTextField label){
        StringBuilder sb = new StringBuilder(label.getText());
        StringBuilder last = new StringBuilder(sb.substring(sb.length()-2));
        if(last.toString().equals(".0")){
            label.setText(sb.substring(0,sb.length()-2));
        }

    }

    //пишем в лейбл результат
    protected void setLabelResult(double before, double after, char oper, JTextField label) {
        switch (oper) {
            case '+': {
                label.setText((before + after) + "");
                cutLabel(label);
                break;
            }
            case '-': {
                label.setText((before - after) + "");
                cutLabel(label);
                break;
            }
            case '/': {
                label.setText((before / after) + "");
                cutLabel(label);
                break;
            }
            case '*': {
                label.setText((before * after) + "");
                cutLabel(label);
                break;
            }
            case '%': {
                label.setText((before % after) + "");
                cutLabel(label);
                break;
            }
            default:
                break;
        }
    }

    public double getBefore() {
        return before;
    }

    public void setBefore(double before) {
        this.before = before;
    }

    public double getAfter() {
        return after;
    }

    public void setAfter(double after) {
        this.after = after;
    }

    public char getOper() {
        return oper;
    }

    public void setOper(char oper) {
        this.oper = oper;
    }
}
