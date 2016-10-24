package ru.kotovalexandr.financemanager.View;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kotovalexandr.financemanager.Controller.Services.SignInOnService;
import ru.kotovalexandr.financemanager.Model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 * Класс описывающий форму для регистрации
 * нового пользователя, получает на вход
 * тот же сокет, кторый создаётся на форме
 * авторизации
 */
public class RegistrationForm extends JFrame {

    private static Logger logger = LoggerFactory.getLogger(RegistrationForm.class);

    private JTextField loginTextField;
    private JTextField passTextField;

    public RegistrationForm() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Регистрация");

        setLayout(layout);
        setBounds(200, 200, 300, 200);
        setResizable(false);

        JLabel welcomeLabel = new JLabel("Введите данные пользователя");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;


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

        JButton okButton = new JButton("Войти");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        okButton.addActionListener(e -> {
            registration();
        });
        add(okButton, gbc);

        JButton cancelButton = new JButton("Отмена");
        gbc.gridy = 4;
        gbc.gridx = 1;
        cancelButton.addActionListener(e -> System.exit(-2));
        add(cancelButton, gbc);

        setVisible(true);
        logger.info("Registration form is visible.");


    }

    /**
     * регистрация нового пользователя и вход
     */
    private void registration() {
        User user = SignInOnService.registrationService(loginTextField.getText(), passTextField.getText());
        if (user != null) {
            new FinManagerFrame(user);
            logger.info("New user with login " + user.getLogin() + " is register.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(getContentPane(), "На сервере уже используется указанный логин.", "Ошибка регистрации", JOptionPane.ERROR_MESSAGE);
            logger.info("User with login " + user.getLogin() + " is already registered.");
        }
    }
}
