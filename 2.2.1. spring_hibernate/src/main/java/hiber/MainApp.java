package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");

        Car car1 = new Car("Usercar", 1);
        Car car2 = new Car("Usercar2", 2);

        user1.setUserCar(car1);
        userService.add(user1);

        user2.setUserCar(car2);
        userService.add(user2);

        userService.add(user3); //для наглядности добавил юзера без авто


// ниже порядок добавления юзера с машиной, если не пользоваться каскадированием
//        userService.add(car1);
//        user1.setUserCar(car1);
//        userService.add(user1);

        // вытащил Юзеров обрано
        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getUserCar());
        }

        System.out.println("__________________________________________");

        // проверка метода getUserByCar
        User userTest = carService.getUserByCar("Usercar", 1);
        System.out.println("Данные о юзере с авто (метода getUserByCar)" + userTest);

        context.close();
    }
}
