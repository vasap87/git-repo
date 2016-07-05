package ru.kotovalex.chat.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * Created by vasilenko.aleksandr on 28.06.2016.
 * Класс для создания подключения к бд и выполнения селектов из базы
 */

public class SQLTools {
    //переменная инстанса
    private static SQLTools instance = new SQLTools();
    //переменные для создания подключения к БД
    private static Connection connection;
    private static PreparedStatement preparedStatement;
//    private static final String PATH = "D:\\Study\\GB\\git-repo\\Java2M\\src\\main\\java\\ru\\geekbrains\\java2\\dz\\dz8\\ВасиленкоАлександр\\Server\\sqlprop.properties";
    private static final String PATH = "C:\\Documents\\Study\\Git-Hub\\Java2M\\src\\main\\java\\ru\\geekbrains\\java2\\dz\\dz8\\ВасиленкоАлександр\\Server\\sqlprop.properties";

    public static SQLTools getInstance(){
        return instance;
    }

    private SQLTools(){

    }

    public void setConnection(){
        Properties dbProp = new Properties();
        try {
            InputStream is = Files.newInputStream(Paths.get(PATH));
            dbProp.load(is);
            String driver = dbProp.getProperty("jdbc.driver");
            String url = dbProp.getProperty("jdbc.url");
            String user = dbProp.getProperty("jdbc.user");
            String pass = dbProp.getProperty("jdbc.pass");
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,pass);
        }catch (SQLException e){
            System.out.println("Не удалось установить соединение с БД");
        }catch (ClassNotFoundException e){
            System.out.println("Не удалось найти библиотеку с драйвером");
        }catch (InvalidPathException e){
            System.out.println("Не удалось найти файл настроек");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisconnection(){
        try {
            connection.close();
        }catch (Exception e){
            System.out.println("не удалось отключиться от БД");
        }
    }

    public String getNickNameByLoginAndPassword(String login, String password){
        String nickname= null;
        try {
            preparedStatement = connection.prepareStatement("SELECT nickname FROM users WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                nickname = resultSet.getString("nickname");
            }
        } catch (SQLException e) {
            System.out.println("Не возможно выполнить селект из базы, причина: "+ e.getMessage());
        }
        return nickname;
    }

    public void registerNewUser(String nickname, String login, String password){
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users (nickname,login,password) VALUES (?,?,?)");
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка регистрации пользователя. Подробенее: "+ e.getMessage());
        }
    }



}
