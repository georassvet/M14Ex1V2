package main.java.realizations;

import main.java.interfaces.IDeveloperRepository;
import main.java.models.Developer;
import main.java.models.Skill;

import java.io.*;
import java.util.*;

public class DeveloperRepository implements IDeveloperRepository {

    public static DeveloperRepository getDeveloperRepository(){
        return new DeveloperRepository();
    }

    static final String filename = "developer.txt";

    @Override
    public Developer add(Developer developer) {
         Collection<Developer> developers = getAll();
         developers.add(developer);
         saveAll(developers);
    return developer;
    }

    private void saveAll(Collection<Developer> developers){
        try(PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Developer developer : developers
                 ) {
                writer.println(developer.toFileString());
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Developer updateDeveloper) {
        Collection<Developer> developers = getAll();

        for (Developer developer : developers
             ) {
            if(developer.getId() == updateDeveloper.getId()){
                developer.setName(updateDeveloper.getName());
                developer.setAge(updateDeveloper.getAge());
                developer.setSkills(updateDeveloper.getSkills());
                break;
            }
        }
        saveAll(developers);
    }

    @Override
    public void delete(Long id) {
        Collection<Developer> developers = getAll();  // get all developers
        Developer developer  = get(id);               // get developer by id
        developers.remove(developer);                 // remove
        saveAll(developers);                          // save to file
    }

    @Override
    public Developer get(Long id) {
        Collection<Developer> developers = getAll();
        for (Developer developer : developers
             ) {
            if(developer.getId()==id){
                return developer;
            }
        }
        return null;
    }

    @Override
    public Collection<Developer> getAll() {
        Collection<Developer> developers  = new ArrayList();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine())!=null){
                String[] lineParams = line.split(",");
                long id = Long.parseLong(lineParams[0]);
                String name = lineParams[1];
                int age = Integer.parseInt(lineParams[2]);

                Set<Skill> skills = new HashSet<>();
                for (int i = 3; i < lineParams.length ; i++) {
                    skills.add(SkillRepository.getRepository().get(Long.parseLong(lineParams[i])));
                }

                developers.add(new Developer(id, name, age, skills));
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return developers;
    }

    public static void main(String[] args) {
        DeveloperRepository developerRepository = DeveloperRepository.getDeveloperRepository();
        developerRepository.add(new Developer("Sergey Mokhov", 28, new String[]{"Java", "C#", "Html", "Css","Sql"}));
        developerRepository.add(new Developer("Pavel Durov", 27, new String[]{"PHP", "SQL", "Html","Sql"}));
        developerRepository.add(new Developer("Ruslan Bashirov", 28, new String[]{"Linux", "Windows", "Mikrotik", "Cisco"}));
        developerRepository.add(new Developer("Petr Ivanov", 35, new String[]{"Linux", "Windows", "Google", "Photoshop"}));
        developerRepository.add(new Developer("Alexey Loy", 20, new String[]{"1C"}));
    }
}
