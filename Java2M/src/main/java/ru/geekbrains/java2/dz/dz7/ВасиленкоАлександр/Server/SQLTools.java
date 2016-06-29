package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Server;

import java.sql.*;

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
    private static final String DBURL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "O33cle#$%";

    public static SQLTools getInstance(){
        return instance;
    }

    private SQLTools(){

    }

    public void setConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DBURL,USER,PASSWORD);
        }catch (SQLException e){
            System.out.println("Не удалось установить соединение с БД");
        }catch (ClassNotFoundException e){
            System.out.println("Не удалось найти ");
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
