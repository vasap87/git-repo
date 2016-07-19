package ru.gb.alexvasilenko.java3.lesson2.fix.dao;

import com.sun.istack.internal.Nullable;
import ru.gb.alexvasilenko.java3.lesson2.fix.Man;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public class ManDaoImpl implements IGenericDao<Man> {
    private Connection conn;

    public ManDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Man man) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO MAMBA_CATALOG (NAME,AGE,SEX) " +
                    "VALUES('" + man.getName() + "', " + man.getAge() +", '" + man.getSex() + "' )";
            //FIXME: сделать сохранение информации о классе ( девочка или мальчик )
            //added sex field into object Man
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Man man) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM MAMBA_CATALOG WHERE ID=" + man.getUid() ;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Man man) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE MAMBA_CATALOG SET NAME='" + man.getName()+"', AGE=" + man.getAge() + ", SEX = '" + man.getSex() + "' WHERE ID=" + man.getUid() ;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public @Nullable Man findById(int id) {
        Man man = null;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM MAMBA_CATALOG WHERE ID=" + id;
            //FIXME: сделать сохранение информации о классе ( девочка или мальчик )
            ResultSet rs = stmt.executeQuery(sql);
            man = new Man(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("sex")) {
                @Override
                public boolean think() {
                    return false;
                }
            };
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return man;
    }

    public List<Man> getAll() {
        List<Man> people = new ArrayList<Man>();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM MAMBA_CATALOG";
            //FIXME: сделать сохранение информации о классе ( девочка или мальчик )
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Man man = new Man(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("sex")) {
                    @Override
                    public boolean think() {
                        return false;
                    }
                };
                people.add(man);
            }
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }
}
