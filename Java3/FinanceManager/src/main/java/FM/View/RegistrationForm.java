package FM.View;

import FM.Dao.DBHelper;
import FM.Dao.SignInOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

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
    private int loginID;

    public RegistrationForm() {

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

    /**
     * регистрация нового пользователя и вход
     */
    private void registration() {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            this.loginID = SignInOn.getInstance().registration(connection,loginTextField.getText(),passTextField.getText());
            if(this.loginID !=0){
                new FinManagerFrame(loginTextField.getText(), loginID);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(getContentPane(), "На сервере уже используется указанный логин.", "Ошибка регистрации", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            logger.error("Error in method registration, detail: "+e.getMessage());
        } finally {
            try {
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                logger.error("Error at finalt part in method registration, detail: "+e.getMessage());
            }
        }
    }
}
