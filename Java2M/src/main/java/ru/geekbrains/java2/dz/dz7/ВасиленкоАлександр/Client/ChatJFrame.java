package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 17.06.2016.
 * Графическое предтавление чата.
 * Вызывается из окна ввода логина и пароля
 *
 * Modify to using sockets at 24.06.2016 by vasilenko.aleksandr
 */

public class ChatJFrame extends JFrame {


    private JTextPane jTextPane;
    private JTextField inputTextField;
    private JButton sendButton;
    private JList loginJList;
    private static DefaultListModel loginList = new DefaultListModel<String>();
    //для работы по сети
    private Socket socket;
    private DataOutputStream out;


    public ChatJFrame(Socket socket) {
        setTitle("Сетевой чат, начало");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        setResizable(false);
        addMenu();
        addChatWindow();
        startConnection(socket);
        setVisible(true);
    }

    private void startConnection(Socket socket) {
        try {
            this.socket = socket;
            out = new DataOutputStream(this.socket.getOutputStream());
            Thread tr = new Thread(new WaitMassageThread(this, socket));
            tr.start();
        } catch (IOException e) {
            System.out.println("Проблема соединения с созданием сокета к серверу");
        }
    }

    private void addChatWindow() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(layout);

        //поле чата со скролом
        jTextPane = new JTextPane();
        jTextPane.setMargin(new Insets(5, 2, 5, 2));
        jTextPane.setEditable(false);
        jTextPane.setContentType("text/html");
        jTextPane.setText("<html><head></head><body></body></html");


        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 8;
        gbc.ipadx = 475;
        gbc.ipady = 400;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(jScrollPane, gbc);

        //логины пользователей
        loginJList = new JList();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 8;
        gbc.ipady = 0;
        gbc.ipadx = 0;
        loginJList.setBorder(new TitledBorder("Собеседники"));
        panel.add(loginJList, gbc);


        //Панель с полем ввода сообщения и кнопкой отправки сообщения

        inputTextField = new JTextField();
        inputTextField.setEnabled(true);
        inputTextField.addActionListener(sendMessageListener());
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        panel.add(inputTextField, gbc);

        sendButton = new JButton("Отправить");
        sendButton.setEnabled(true);
        sendButton.addActionListener(sendMessageListener());
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(sendButton, gbc);

        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    out.writeUTF("quit\texit");
                    out.flush();
                    out.close();
                    socket.close();
                } catch (IOException e1) {
                    System.out.println("Проблема закрытия сокета");
                    System.exit(-1);
                }

            }
        });

    }

    private ActionListener sendMessageListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    out.writeUTF("send\t"+inputTextField.getText());
                    out.flush();
                    inputTextField.setText("");
                    inputTextField.grabFocus();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        };
    }

    protected void addMessageToJTextPane(String s) {
        String temp = jTextPane.getText();
        int index = temp.lastIndexOf("</body>");
        temp = temp.substring(0, index) + " " + s + " " + temp.substring(index);
        jTextPane.setText(temp);
    }

    //Дописываем в файл то, что отправили в чат
    protected void writeInFile(String addText) {
        File historyFile;
        FileWriter fileWriter = null;
        try {
            historyFile = new File("history.txt");
            //если нет файла - создаём
            historyFile.createNewFile();
            //пишем в конец файла
            fileWriter = new FileWriter(historyFile, true);
            fileWriter.write(addText+'\n');
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Проблема с созданием файла");
        } finally {

            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Проблема с закрытием файла");
            }

        }
    }

    protected void addUserToList(String users){
        String[] activUser = users.split("\t");
        loginJList.setListData(activUser);
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        Font menuFont = new Font("Veranda", Font.PLAIN, 12);
        //меню игра
        JMenu menu = new JMenu("Чат");
        menu.setFont(menuFont);

        //заного
//        JMenuItem reTry = new JMenuItem("Выбор логина");
//        reTry.setFont(menuFont);
//        reTry.addActionListener(e -> {
//           // new LoginJFrame();
//            this.dispose();
//        });
//        menu.add(reTry);

        //очистить чат
        JMenuItem clearChat = new JMenuItem("Очистить окно чата");
        clearChat.setFont(menuFont);
        clearChat.addActionListener(e -> jTextPane.setText("<html><head></head><body></body></html"));
        menu.add(clearChat);

        menu.addSeparator();
        //выход
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(menuFont);
        menu.add(exit);
        exit.addActionListener(e -> System.exit(0));
        menuBar.add(menu);

        //о программе
        JMenu about = new JMenu("About");
        about.setFont(menuFont);
        JMenuItem aboutItem = new JMenuItem("О программе");
        aboutItem.setFont(menuFont);
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(getContentPane(), "Задание по уроку 7 курса Java2\n\nВыполнил Василенко Александр", "О программе", JOptionPane.INFORMATION_MESSAGE));
        about.add(aboutItem);

        menuBar.add(about);
        setJMenuBar(menuBar);
    }

}
