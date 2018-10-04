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

        developerController.addDeveloper(developer);

        Developer getDeveloper1 = developerController.getDeveloper(1);
        Developer getDeveloper100 = developerController.getDeveloper(100);
        System.out.println(getDeveloper1);


    }
}
