package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
       AnnotationConfigApplicationContext context =
               new AnnotationConfigApplicationContext(AppConfig.class);

       System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
       System.out.println("Adding cars  and getting them back\n");

       Car car1 = new Car("Car Model 1", 1);
       Car car2 = new Car("Car Model 2", 2);
       Car car3 = new Car("Car Model 3", 3);
       Car car4 = new Car("Car Model 4", 4);

       CarService carService = context.getBean(CarService.class);

       carService.add(car1);
       carService.add(car2);
       carService.add(car3);
       carService.add(car4);

       List<Car> cars = carService.listCars();
       System.out.println();
       for (Car car : cars){
           System.out.println(car);
           System.out.println();
       }

       System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
       System.out.println("Adding users and getting them back\n");

       UserService userService = context.getBean(UserService.class);

       userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
       userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
       userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
       userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

       List<User> users = userService.listUsers();
       System.out.println();
       for (User user : users) {
           System.out.println(user);
           System.out.println();
       }

       System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
       System.out.println("Finding user by car info\n");

       String carModel = "Car Model 1";
       int carSeries = 1;

       System.out.println();
       System.out.println("The user that owns car with model: " + carModel +
               " and series: " + carSeries + " is user:\n" +
               userService.getUserByCarInfo(carModel, carSeries));
       System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

       context.close();
   }
}
