package ru.kotovalexandr.financemanager.View;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kotovalexandr.financemanager.Controller.Services.SignInOnService;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Dao.SignInOn;
import ru.kotovalexandr.financemanager.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 * Класс для создания окна авторизации пользователя
 */
public class LoginJFrame extends JFrame {
    private static Logger logger = LoggerFactory.getLogger(LoginJFrame.class);


    public LoginJFrame() {


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Авторизация");

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
        password.addActionListener(e -> authorisation(login.getText(), password.getText()));
        gbc.gridx = 1;
        add(password, gbc);

        JButton enterButton = new JButton("Вход");
        gbc.gridy = 2;
        gbc.gridx = 0;
        enterButton.addActionListener(e -> authorisation(login.getText(), password.getText()));
        add(enterButton, gbc);

        JButton cancelButton = new JButton("Отмена");
        gbc.gridx = 1;
        cancelButton.addActionListener(e -> System.exit(3));
        add(cancelButton, gbc);

        JButton registrationButton = new JButton("Регистрация");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        registrationButton.addActionListener(e -> {
            new RegistrationForm();
            this.dispose();
        });
        add(registrationButton, gbc);

        setVisible(true);
        logger.info("Login form is visible.");

    }

    private void authorisation(String login, String password) {
        User user = SignInOnService.authorisationService(login,password);
        if (user != null) {
            new FinManagerFrame(user);
            logger.info("User with login " + user.getLogin() + " is login.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(getContentPane(), "На сервере не найдена указанная комбинация логина и пароля.", "Ошибка авторизации", JOptionPane.ERROR_MESSAGE);
            logger.info("User with login " + user.getLogin() + " and password is not register.");
        }
    }
}

