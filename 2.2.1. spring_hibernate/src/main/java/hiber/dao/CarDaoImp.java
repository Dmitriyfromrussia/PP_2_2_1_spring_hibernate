package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User getUserByCar(String model, int series) { //User
        List<User> allUsers = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.userCar", User.class).getResultList();

        for (User user : allUsers) {
            if (user.getUserCar().getModel().equals(model) & user.getUserCar().getSeries() == series) return user;
            break;
        }
        return null;
    }
}
