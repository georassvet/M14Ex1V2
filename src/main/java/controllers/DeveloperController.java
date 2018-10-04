package main.java.controllers;

import main.java.interfaces.IDeveloperRepository;
import main.java.models.Developer;
import main.java.realizations.DeveloperRepository;

import java.util.Collection;
import java.util.List;

public class DeveloperController {
    private IDeveloperRepository developerRepository;

    public DeveloperController(){
        developerRepository = DeveloperRepository.getDeveloperRepository();
    }
    public void addDeveloper(Developer developer){
        developerRepository.add(developer);
    }
    public void removeDeveloper(long id){
        developerRepository.delete(id);
    }
    public void updateDeveloper(Developer developer){
        developerRepository.update(developer);
    }
    public Developer getDeveloper(long id){
        return developerRepository.get(id);
    }
    public Collection<Developer> getDevelopers(){
        return  developerRepository.getAll();
    }
}
