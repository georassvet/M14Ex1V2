package main.java.controllers;

import main.java.repository.IDeveloperRepository;
import main.java.models.Developer;
import main.java.repository.io.DeveloperRepository;

import java.util.Collection;

public class DeveloperController {
    private IDeveloperRepository developerRepository;

    public DeveloperController(){
        developerRepository = new DeveloperRepository();
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
