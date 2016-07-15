package ru.gb.alexvasilenko.java3.lesson2.FinManager.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.alexvasilenko.java3.lesson2.FinManager.Dao.DBHelper;
import ru.gb.alexvasilenko.java3.lesson2.FinManager.Dao.SignInOn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 * Класс для создания окна авторизации пользователя
 */
public class LoginJFrame extends JFrame {
    private static Logger logger = LoggerFactory.getLogger(LoginJFrame.class);

    private String login;

    public LoginJFrame() {


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
        password.addActionListener(e -> authorisation(login.getText(), password.getText()));
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
            new RegistrationForm();
            this.dispose();
        });
        add(registrationButton, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    DBHelper.getInstance().closeConnection();
                } catch (SQLException e1) {
                    logger.error("Error on window close, detail: "+e1.getMessage());
                }
            }
        });

        setVisible(true);

    }

    private void authorisation(String login, String password) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            this.login = SignInOn.getInstance().authorisation(connection,login,password);
            if(this.login!=null){
                new FinManagerFrame(login);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(getContentPane(), "На сервере не найдена указанная комбинация логина и пароля.", "Ошибка авторизации", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error("Error in method authorisation, detail: "+e.getMessage());
        } finally {
            try {
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                logger.error("Error at finalt part in method authorisation, detail: "+e.getMessage());
            }
        }

    }
}

