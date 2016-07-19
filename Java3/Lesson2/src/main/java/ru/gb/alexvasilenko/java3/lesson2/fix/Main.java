package ru.gb.alexvasilenko.java3.lesson2.fix;

import ru.gb.alexvasilenko.java3.lesson2.fix.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        List<Man> people = new ArrayList<Man>();
        people.add(new Girl(1, "Anna", 23));
        people.add(new Girl(2, "Masha", 24));
        people.add(new Girl(3, "Kataj", 25));
        people.add(new Girl(4, "Lena", 18));
        people.add(new Boy(5, "Kolja", 22));
        people.add(new Boy(6, "Vasja", 18));
        people.add(new Boy(7, "Misha", 27));

        IDaoFactory iDaoFactory = new MambaDaoFactory();
        try {
            Connection conn = iDaoFactory.getConnection();
            DaoUtil.createScheme(conn);
            IGenericDao<Man> manDao = new ManDaoImpl(conn);

            for (Man man:
                 people) {
                manDao.save(man);
            }

            people.get(0).setAge(24);
            manDao.update(people.get(0));
            manDao.remove(people.get(1));

            for (Man man : manDao.getAll()){
                System.out.println(man);
            }

            DaoUtil.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
