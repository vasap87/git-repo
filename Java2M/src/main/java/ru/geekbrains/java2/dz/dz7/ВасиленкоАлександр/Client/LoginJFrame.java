package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 20.06.2016.
 * Класс для создания окна авторизации пользователя
 */
public class LoginJFrame extends JFrame {

    private static final String HOST = "localhost";
    private static final int PORT = 8188;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public LoginJFrame() {

        try {
            socket = new Socket(HOST, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Вход в чат");

        setLayout(layout);
        setBounds(200, 200, 200, 150);
        setResizable(false);

        JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setSize(100, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginLabel, gbc);

        JTextField login = new JTextField();
        login.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(login, gbc);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setSize(100, 20);
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(passwordLabel, gbc);

        JPasswordField password = new JPasswordField();
        password.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        add(password, gbc);

        JButton enterButton = new JButton("Вход");
        gbc.gridy = 2;
        gbc.gridx = 0;
        enterButton.addActionListener(e -> {
            authorisation(login.getText(), password.getText());
        });
        add(enterButton, gbc);

        JButton canselButton = new JButton("Отмена");
        gbc.gridx = 1;
        canselButton.addActionListener(e -> System.exit(3));
        add(canselButton, gbc);

        JButton registrationButton = new JButton("Регистрация");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        registrationButton.addActionListener(e -> {
            new RegistrationForm(socket);
            this.dispose();
        });
        add(registrationButton, gbc);

        setVisible(true);

    }

    private void authorisation(String login, String password) {
        try {
            out.writeUTF("authorisation\t" + login + "\t" + password);
            out.flush();
            String s = in.readUTF();
            if (s != null) {
                if (s.equals("good")) {
                    new ChatJFrame(socket);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "На сервере не найдена указанная комбинация логина и пароля.", "Ошибка авторизации", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }
}
