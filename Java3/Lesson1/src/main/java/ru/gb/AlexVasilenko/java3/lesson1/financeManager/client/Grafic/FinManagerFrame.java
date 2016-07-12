package ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Renders.ListAccountRender;
import ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Renders.ListTransactionRender;
import ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Tools.Account;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class FinManagerFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private static Logger logger = LoggerFactory.getLogger(FinManagerFrame.class);



    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private GridBagConstraints constraints = new GridBagConstraints();
    private String login;

    public FinManagerFrame(Socket socket, String login){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,WIDTH+20,HEIGHT+80);
        setResizable(false);
        setTitle("Финансы пользователя: "+login);
        this.socket = socket;
        try {
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            logger.error("Ошибка при создании потоков с сокетом "+socket.toString());
        }
        this.login = login;

        setLayout(new GridBagLayout());

        addAccountPanel();
        addTransactionPanel();

        addingData();
        setVisible(true);
    }

    private void addingData() {
        try {
            out.writeUTF("getAccounts\t");
            out.flush();
            String[] fromServer = in.readUTF().split("\t");
            if(fromServer[0].equals("accounts")){
                Gson gson = new Gson();
                java.lang.reflect.Type typeAccount = new TypeToken<ArrayList<Account>>() {
                }.getType();
                ArrayList<Account> accountList = gson.fromJson(fromServer[1],typeAccount);
            }
        } catch (IOException e) {
            logger.error("Ошибка в методе addingData");
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
        JList accountList = new JList();
        accountList.setCellRenderer(new ListAccountRender());
        JScrollPane jScrollPane = new JScrollPane(accountList);
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
