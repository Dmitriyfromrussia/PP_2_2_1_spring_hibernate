package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Optional<User> getUserByCar(String model, int series) {
        List<User> allUsers = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.userCar", User.class).getResultList();

        for (User user : allUsers) {
            if (user.getUserCar().getModel().equals(model) && user.getUserCar().getSeries() == series) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    //    @Override
//    public User getUserByCar(String model, int series) {
//        List<User> allUsers = sessionFactory.getCurrentSession()
//                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.userCar", User.class).getResultList();
//
//        for (User user : allUsers) {
//            if (user.getUserCar().getModel().equals(model) & user.getUserCar().getSeries() == series) return user;
//            break;
//        }
//        return null;
//    }
}
