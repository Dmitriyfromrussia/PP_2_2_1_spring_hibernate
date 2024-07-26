package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car1 = new Car("Usercar", 1);
        Car car2 = new Car("Usercar2", 2);

        userService.add(user1);
        car1.setCarUser(user1);
        userService.add(car1);

        userService.add(user2);
        car2.setCarUser(user2);
        userService.add(car2);

        for (User user : userService.listUsers()) { // вытащил Userov обрано
            System.out.println(user + " " + user.getUserCar());
        }

        System.out.println("__________________________________________");

        User userTest = userService.getUserByCar("Usercar", 1); // проверка метода getUserByCar
        System.out.println("Данные о юзере с авто (метода getUserByCar)" + userTest);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru111"));

//        List<User> users = userService.listUsers();
//        for (User user : users) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//            System.out.println();
//        }
        context.close();
    }
}
