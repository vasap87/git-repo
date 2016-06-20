package ru.geekbrains.java2.dz.dz4.ВасиленкоАлександр;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 20.06.2016.
 */
public class LoginJFrame extends JFrame {

    public LoginJFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setTitle("Вход в чат");

        setLayout(layout);
        setBounds(200,200,200,120);
        setResizable(false);

        JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setSize(100,20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginLabel, gbc);

        JTextField login = new JTextField();
        login.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(login, gbc);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setSize(100,20);
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
        enterButton.addActionListener(e -> {new ChatJFrame(login.getText()); this.dispose();});
        add(enterButton, gbc);

        JButton canselButton = new JButton("Отмена");
        gbc.gridx = 1;
        canselButton.addActionListener(e -> System.exit(3));
        add(canselButton,gbc);


        setVisible(true);

    }
}
