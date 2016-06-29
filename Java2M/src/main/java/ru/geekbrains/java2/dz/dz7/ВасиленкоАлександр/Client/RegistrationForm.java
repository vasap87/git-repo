package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Client;

import ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Server.SQLTools;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 29.06.2016.
 * Класс описывающий форму для регистрации
 * нового пользователя, получает на вход
 * тот же сокет, кторый создаётся на форме
 * авторизации
 */
public class RegistrationForm extends JFrame {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private JTextField nickTextField;
    private JTextField loginTextField;
    private JTextField passTextField;

    public RegistrationForm(Socket socket) {
        try {
            this.socket = socket;
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Регистрация нового пользователя");

        setLayout(layout);
        setBounds(200, 200, 300, 200);
        setResizable(false);

        JLabel welcomeLabel = new JLabel("Введите данные нового пользователя");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);

        JLabel nickLabel = new JLabel("Ник:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nickLabel, gbc);

        nickTextField = new JTextField();
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(nickTextField, gbc);

        JLabel loginLabel = new JLabel("Логин:");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(loginLabel, gbc);

        loginTextField = new JTextField();
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(loginTextField, gbc);

        JLabel passLabel = new JLabel("Пароль:");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(passLabel, gbc);

        passTextField = new JTextField();
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(passTextField, gbc);

        JButton okButton = new JButton("Зарегистрироваться и войти");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        okButton.addActionListener(e -> {
            registration();
        });
        add(okButton, gbc);

        JButton canselButton = new JButton("Oтмена");
        gbc.gridy = 4;
        gbc.gridx = 1;
        canselButton.addActionListener(e -> System.exit(-2));
        add(canselButton, gbc);

        setVisible(true);


    }

    /**
     * регистрация нового пользователя и вход
     */
    private void registration() {
        try {
            //отправляем строку на сервер
            out.writeUTF("registration\t" + nickTextField.getText() + "\t" + loginTextField.getText() + "\t" + passTextField.getText());
            out.flush();
            //если успешная регистрация
            if (in.readUTF().equals("good_reg")) {
                //происходит авторизация
                out.writeUTF("authorisation\t" + loginTextField.getText() + "\t" + passTextField.getText());
                out.flush();
                new ChatJFrame(socket);
                this.dispose();
            }
            //если не успешно, значит уже используется такая комбинация логина и пароля
            else {
                loginTextField.setText("");
                passTextField.setText("");
                JOptionPane.showMessageDialog(getContentPane(), "На сервере уже используется такая комбинация логина и пароля.", "Ошибка регистрации", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
