package main.java.controllers;

import main.java.models.Skill;
import main.java.repository.io.SkillRepository;

import java.util.Collection;

public class SkillController {
    private SkillRepository repository;

    public SkillController(){
        repository= new SkillRepository();
    }

    public void addSkill(Skill skill){
        repository.save(skill);
    }

    public void deleteSkill(long id){
        repository.delete(id);
    }

    public void updateSkill(Skill skill){
        repository.update(skill);
    }

    public Skill getSkill(long id){
        return repository.getById(id);
    }

    public Collection<Skill> getSkills(){
        return repository.getAll();
    }





}
