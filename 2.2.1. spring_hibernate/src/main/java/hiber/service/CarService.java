package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface CarService {
    User getUserByCar(String model, int series); //добавил
}
