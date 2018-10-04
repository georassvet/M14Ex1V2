package main.java.controllers;

import main.java.models.Skill;
import main.java.realizations.SkillRepository;

public class SkillController {
    private SkillRepository repository;

    public SkillController(){
        repository=SkillRepository.getRepository();
    }

    public void addSkill(Skill skill){
        repository.add(skill);
    }

    public void deleteSkill(long id){
        repository.delete(id);
    }

    public void updateSkill(Skill skill){
        repository.update(skill);
    }





}
