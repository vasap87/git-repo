package ru.vasilenkoaleksandr.myprojects.Chat;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 17.06.2016.
 * Графическое предтавление чата.
 * Вызывается из окна ввода логина и пароля
 */

public class ChatJFrame extends JFrame {


    private JTextArea jTextArea;
    private JTextField inputTextField;
    private JButton sendButton;
    private static DefaultListModel loginList = new DefaultListModel<String>();
    private String toLogin = "";
    private String login;


    public ChatJFrame(String login) {
        this.login = login;
        loginList.addElement(login);
        setTitle("Сетевой чат, начало");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        setResizable(false);
        addMenu();
        addChatWindow();
        setVisible(true);
    }

    private void addChatWindow() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(layout);

        //поле чата со скролом
        //переделать на JTextPane
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setEnabled(true);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        Font textAreaFont = new Font("Veranda", Font.PLAIN, 16);
        jTextArea.setFont(textAreaFont);
        jTextArea.setMargin(new Insets(5, 2, 5, 2));

        JScrollPane jScrollPane = new JScrollPane(jTextArea);
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
        JList loginJList = new JList(loginList);
        loginJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!login.equals(loginJList.getSelectedValue())) {
                    toLogin = "to [" + (String) loginJList.getSelectedValue() + "]";
                }
            }
        });
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

    }

    private ActionListener sendMessageListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GregorianCalendar calendar = new GregorianCalendar();
                StringBuilder currentDate = new StringBuilder();
                currentDate
                        //день месяца
                        .append((calendar.get(Calendar.DAY_OF_MONTH) < 10 ? 0 + "" + calendar.get(Calendar.DAY_OF_MONTH) : calendar.get(Calendar.DAY_OF_MONTH)) + ".")
                        //месяц
                        .append((calendar.get(Calendar.MONTH) < 9 ? 0 + "" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1)) + ".")
                        //год
                        .append(calendar.get(Calendar.YEAR) + " ")
                        //час
                        .append((calendar.get(Calendar.HOUR_OF_DAY) < 10 ? 0 + "" + calendar.get(Calendar.HOUR_OF_DAY) : calendar.get(Calendar.HOUR_OF_DAY)) + ":")
                        //минуты
                        .append((calendar.get(Calendar.MINUTE) < 10 ? 0 + "" + calendar.get(Calendar.MINUTE) : calendar.get(Calendar.MINUTE)) + ":")
                        //секунды
                        .append((calendar.get(Calendar.SECOND) < 10 ? 0 + "" + calendar.get(Calendar.SECOND) : calendar.get(Calendar.SECOND)));

                StringBuilder sbInput = new StringBuilder("[" + currentDate.toString() + "] " + login + " "
                        + (!toLogin.equals("") ? toLogin + " " : "") + ": " + inputTextField.getText() + '\n');
                jTextArea.append(sbInput.toString());
                writeInFile(sbInput.toString());
                inputTextField.setText("");
                toLogin = "";

            }
        };
    }

    //Дописываем в файл то, что отправили в чат
    private void writeInFile(String addText) {
        File historyFile;
        FileWriter fileWriter = null;
        try {
            historyFile = new File("history.txt");
            //если нет файла - создаём
            historyFile.createNewFile();
            //пишем в конец файла
            fileWriter = new FileWriter(historyFile, true);
            fileWriter.write(addText);
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

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        Font menuFont = new Font("Veranda", Font.PLAIN, 12);
        //меню игра
        JMenu menu = new JMenu("Чат");
        menu.setFont(menuFont);

        //заного
        JMenuItem reTry = new JMenuItem("Выбор логина");
        reTry.setFont(menuFont);
        reTry.addActionListener(e -> {
            new LoginJFrame();
            this.dispose();
        });
        menu.add(reTry);

        //очистить чат
        JMenuItem clearChat = new JMenuItem("Очистить окно чата");
        clearChat.setFont(menuFont);
        clearChat.addActionListener(e -> jTextArea.setText(""));
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
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(getContentPane(), "Задание по уроку 4 курса Java2\n\nВыполнил Василенко Александр", "О программе", JOptionPane.INFORMATION_MESSAGE));
        about.add(aboutItem);

        menuBar.add(about);
        setJMenuBar(menuBar);
    }

}
