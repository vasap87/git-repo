package ru.geekbrains.java2.dz.dz4.ВасиленкоАлександр;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by vasilenko.aleksandr on 17.06.2016.
 * Графическое предтавление чата.
 * Объект данного класса необходим в единичном екземпляре,
 * по этому запретим открытый доступ к констрктору
 */

public class Chat extends JFrame {
    private static Chat instance = new Chat();

    private JTextArea jTextArea;
    private JTextField inputTextField;
    private JTextField loginTextField;
    private JButton confirmLoginButton;
    private JButton sendButton;

    private String login;


    public static Chat getInstance() {
        return instance;
    }

    private Chat() {
        setTitle("Сетевой чат, начало");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 600);
        setResizable(false);
        addMenu();
        addChatWindow();
        setVisible(true);
    }

    private void addChatWindow() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //поле ввода логина
        Font loginFont = new Font("Veranda",Font.BOLD,18);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.LINE_AXIS));
        loginTextField = new JTextField();
        loginTextField.setFont(loginFont);
        loginTextField.setHorizontalAlignment(SwingConstants.CENTER);

        loginTextField.addActionListener(sendLoginListener());
        loginPanel.add(loginTextField);
        confirmLoginButton = new JButton("Начать чат");
        confirmLoginButton.addActionListener(sendLoginListener());
        loginPanel.add(confirmLoginButton);
        panel.add(loginPanel, BorderLayout.NORTH);

        //поле чата со скролом
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setEnabled(false);
        jTextArea.setLineWrap(true);
        Font textAreaFont = new Font("Veranda",Font.PLAIN,16);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        panel.add(jScrollPane, BorderLayout.CENTER);

        //Панель с полем ввода сообщения и кнопкой отправки сообщения
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
        inputTextField = new JTextField();
        inputTextField.setEnabled(false);

        inputTextField.addActionListener(sendMessageListener());
        inputPanel.add(inputTextField);

        sendButton = new JButton("Отправить");
        sendButton.setEnabled(false);
        sendButton.addActionListener(sendMessageListener());
        inputPanel.add(sendButton);
        panel.add(inputPanel, BorderLayout.SOUTH);
        add(panel);

    }

    private ActionListener sendLoginListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = loginTextField.getText();

                loginTextField.setEnabled(false);
                confirmLoginButton.setEnabled(false);

                jTextArea.setEnabled(true);
                inputTextField.setEnabled(true);
                inputTextField.setFocusable(true);
                sendButton.setEnabled(true);
            }
        };
    }

    private ActionListener sendMessageListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GregorianCalendar calendar = new GregorianCalendar();
                StringBuilder currentDate = new StringBuilder();
                currentDate
                        //день месяца
                        .append((calendar.get(Calendar.DAY_OF_MONTH)<10?0+""+calendar.get(Calendar.DAY_OF_MONTH):calendar.get(Calendar.DAY_OF_MONTH))+".")
                        //месяц
                        .append((calendar.get(Calendar.MONTH)<9?0+""+(calendar.get(Calendar.MONTH)+1):(calendar.get(Calendar.MONTH)+1))+".")
                        //год
                        .append(calendar.get(Calendar.YEAR)+" ")
                        //час
                        .append((calendar.get(Calendar.HOUR_OF_DAY)<10?0+""+calendar.get(Calendar.HOUR_OF_DAY):calendar.get(Calendar.HOUR_OF_DAY))+":")
                        //минуты
                        .append((calendar.get(Calendar.MINUTE)<10?0+""+calendar.get(Calendar.MINUTE):calendar.get(Calendar.MINUTE))+":")
                        //секунды
                        .append((calendar.get(Calendar.SECOND)<10?0+""+calendar.get(Calendar.SECOND):calendar.get(Calendar.SECOND)));

                StringBuilder sbTextArea = new StringBuilder(jTextArea.getText());
                StringBuilder sbInput = new StringBuilder("["+currentDate.toString()+"]"+" "+login+": "+inputTextField.getText()+'\n');
                sbTextArea.append(sbInput);
                jTextArea.setText(sbTextArea.toString());
                writeInFile(sbInput.toString());
                inputTextField.setText("");

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
        }finally {

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
        reTry.addActionListener(e -> renameUser());
        menu.add(reTry);
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
        aboutItem.addActionListener(e -> JOptionPane.showInternalMessageDialog(getContentPane(),"Задание по уроку 4 курса Java2\n\nВыполнил Василенко Александр","О программе",JOptionPane.INFORMATION_MESSAGE));
        about.add(aboutItem);

        menuBar.add(about);
        setJMenuBar(menuBar);
    }

    //для перименования пользователя
    private void renameUser() {
        loginTextField.setText("");
        loginTextField.setEnabled(true);
        confirmLoginButton.setEnabled(true);

        jTextArea.setEnabled(false);
        inputTextField.setEnabled(false);
        sendButton.setEnabled(false);
    }
}
