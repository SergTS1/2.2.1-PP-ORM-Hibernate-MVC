package hiber;

import hiber.model.Car;
import hiber.config.AppConfig;
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

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      carService.add(new Car("Жигули", 6));
      carService.add(new Car("Лада", 8));
      carService.add(new Car("Москвич", 412));
      carService.add(new Car("Волга", 783));

      User user0 = new User("User5", "Lastname5", "user5@mail.ru");
      Car car = new Car("Запорожец", 3);
      User user1 = new User("User6", "Lastname6", "user6@mail.ru");
      Car car1 = new Car("ВАЗ", 21102);
      User user2 = new User("User7", "Lastname7", "user7@mail.ru");
      Car car2 = new Car("АвтоВаз", 2115);
      user0.setUserCar(car);
      userService.add(user0);
      user1.setUserCar(car1);
      userService.add(user1);
      user2.setUserCar(car2);
      userService.add(user2);

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getUserCar());
      }

//      User us = userService.listUsers().get(4);
//      User us1 = userService.listUsers().get(5);
//      User us2 = userService.listUsers().get(6);
//      System.out.println(us);
//      System.out.println(us1);
//      System.out.println(us2);

      System.out.println(userService.getUserByModelAndSeries("ВАЗ", 21102));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("User Car Info = " + user.getUserCar());
         System.out.println();
      }

      context.close();
   }
}
