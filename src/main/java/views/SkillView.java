package main.java.views;

import main.java.controllers.SkillController;
import main.java.models.Skill;

public class SkillView {
    public static void main(String[] args) {
        SkillController skillController =new SkillController();
        for (Skill skill: skillController.getSkills()
             ) {
            System.out.println(skill);
        }
    }
}
