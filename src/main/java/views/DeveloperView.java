package main.java.views;

import main.java.controllers.DeveloperController;
import main.java.models.Developer;

public class DeveloperView {
    public static void main(String[] args) {
        DeveloperController developerController =new DeveloperController();
        for (Developer developer : developerController.getDevelopers()
             ) {
            System.out.println(developer);
        }

        Developer developer = new Developer("Sergey Mokhov",28,"Engeener",new String[]{"Java"});
        Developer developer1 = new Developer("Alexey Loy",35,"Support specialist",new String[]{"1C", "TCP", "IP", "Routing"});
        Developer developer2 = new Developer("Pavel Durov",32,"Developer",new String[]{"PHP","SQL","nginx"});

        developerController.addDeveloper(developer);
        developerController.addDeveloper(developer1);
        developerController.addDeveloper(developer2);

        Developer getDeveloperS = developerController.getDeveloper(1);
       //Developer getDeveloper100 = developerController.getDeveloper(100);
        System.out.println(getDeveloperS);

        System.out.println();
        for (Developer d : developerController.getDevelopers()
                ) {
            System.out.println(d);
        }


    }
}
