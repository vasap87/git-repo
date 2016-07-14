package ru.gb.alexvasilenko.java3.lesson2.FinManager.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.alexvasilenko.java3.lesson2.FinManager.Model.Renders.ListAccountRender;
import ru.gb.alexvasilenko.java3.lesson2.FinManager.Model.Renders.ListTransactionRender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class FinManagerFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private static Logger logger = LoggerFactory.getLogger(FinManagerFrame.class);



    private GridBagConstraints constraints = new GridBagConstraints();
    private String login;

    private JList accountJList;

    public FinManagerFrame(String login){
        this.login = login;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,WIDTH+20,HEIGHT+80);
        setResizable(false);
        setTitle("Финансы пользователя: "+this.login);



        setLayout(new GridBagLayout());

        addAccountPanel();
        addTransactionPanel();

        addingData();
        setVisible(true);
    }

    private void addingData() {

    }

    private void setItemsToJList(ArrayList arrayList, JList controlJList) {
        if(!arrayList.isEmpty()){
            Object arr[] = arrayList.toArray();
            controlJList.setListData(arr);
        }
    }

    //добавление панели со счетами пользователя
    private void addAccountPanel() {
        JPanel accountPanel = new JPanel(new BorderLayout());
        //кнопки
        JPanel buttonAccoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addAccountButton = new JButton("+");
        buttonAccoutPanel.add(addAccountButton);
        JButton removeAccountButton = new JButton("-");
        buttonAccoutPanel.add(removeAccountButton);
        accountPanel.add(buttonAccoutPanel,BorderLayout.NORTH);
        //листбокс со счетами
        accountJList = new JList();
        accountJList.setCellRenderer(new ListAccountRender());
        JScrollPane jScrollPane = new JScrollPane(accountJList);
        jScrollPane.setPreferredSize(new Dimension(WIDTH/3,HEIGHT));
        accountPanel.add(jScrollPane, BorderLayout.CENTER);

        //добавление панели на форму
        constraints.gridx=0;
        constraints.gridy=0;
        add(accountPanel, constraints);

    }

    private void addTransactionPanel() {
        JPanel transactionPanel = new JPanel(new BorderLayout());
        //кнопки
        JPanel buttonTransactionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addTransactionButton = new JButton("+");
        buttonTransactionPanel.add(addTransactionButton);
        JButton removeTransactionButton = new JButton("-");
        buttonTransactionPanel.add(removeTransactionButton);
        transactionPanel.add(buttonTransactionPanel,BorderLayout.NORTH);
        //листбокс со счетами
        JList transactionList = new JList();
        transactionList.setCellRenderer(new ListTransactionRender());
        JScrollPane jScrollPane = new JScrollPane(transactionList);
        jScrollPane.setPreferredSize(new Dimension(2*WIDTH/3,HEIGHT));
        transactionPanel.add(jScrollPane, BorderLayout.CENTER);

        //добавление панели на форму
        constraints.gridx=1;
        constraints.gridy=0;
        add(transactionPanel, constraints);

    }

}
