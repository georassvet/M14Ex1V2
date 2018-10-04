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
    }
}
