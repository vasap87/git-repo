package ru.geekbrains.java1.dz.dz8.AlexVasilenko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vasilenko.aleksandr on 31.05.2016.
 */
public class OperButtons extends JButton {
    private char res;

    public OperButtons(String title, char num, JTextField label, Calc c){
        super(title);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //если последний символ число
                if(Character.isDigit(label.getText().charAt(label.getText().length()-1))){
                    //если это первое число в операции
                    if(c.getBefore()==0){
                        //берём из него первую последовательнось цифр
                        //long a = getFirstNum(label);
                        double a = Double.parseDouble(label.getText());
                        //пишем в переменную "до знака"
                        c.setBefore(a);
                    }else{ //если второе
                        StringBuilder last = new StringBuilder(label.getText());
                        double b = getLastNum(last);
                        //пишем последюю последовательность в "после знака"
                        c.setAfter(b);
                        //пишем в лейбл результат
                        c.setLabelResult(c.getBefore(),c.getAfter(),c.getOper(),label);
                        //пишем в переменную "до знака" результат из лейбла
                        c.setBefore(Double.parseDouble(label.getText()));
                        c.setAfter(0);
                    }
                }
                //если последний сивол не чилсо, то образаем его и пишем новый
                else{
                    label.setText(getFirstNum(label)+"");
                }

                StringBuilder sb = new StringBuilder(label.getText());
                sb.append(num);
                label.setText(sb.toString());
                c.setOper(num);
            }
        });
    }
    //возвращает последние введённые символы
    private double getLastNum(StringBuilder last) {
        StringBuilder res = new StringBuilder();
        for (int i = last.length()-1; i >= 0 ; i--) {
            if(Character.isDigit(last.charAt(i))||last.charAt(i)=='.'){
                res.append(last.charAt(i));
            }else break;
        }
        res.reverse();
        return Double.parseDouble(res.toString());
    }

    //возращает первую последовательность цифр
    private double getFirstNum(JTextField label){
        StringBuilder sb = new StringBuilder(label.getText());
        StringBuilder res = new StringBuilder();
        if(sb.charAt(0)=='-'){
            sb.substring(1,sb.length()-1);
            for (int i = 1; i <sb.length(); i++) {
                if(Character.isDigit(sb.charAt(i))||sb.charAt(i)=='.'){
                    res.append(sb.charAt(i));
                }
                else break;
            }
            return Double.parseDouble("-"+res.toString());
        }else{
            for (int i = 1; i <sb.length(); i++) {
                if(Character.isDigit(sb.charAt(i))||sb.charAt(i)=='.'){
                    res.append(sb.charAt(i));
                }
                else break;
            }
            return Long.parseLong(res.toString());
        }

    }

}
