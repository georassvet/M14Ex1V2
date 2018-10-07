package main.java.repository.io;

import main.java.repository.ISkillRepository;
import main.java.models.Skill;

import java.io.*;
import java.util.*;

public class SkillRepository implements ISkillRepository {
    final String filename = "skills.txt";

   private void saveAll(Collection<Skill> skills){
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Skill skill : skills
                 ) {
                printWriter.println(skill);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Skill add(Skill skill) {
        Collection<Skill> skills = getAll();
        skills.add(skill);
        saveAll(skills);
        return skill;
    }
    @Override
    public Skill getSkillByName(String nameParam){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line=reader.readLine())!=null){
                String[] lineParams = line.split(",");
                String name = lineParams[1];
                if(name.equals(nameParam)){
                    Long id = Long.parseLong(lineParams[0]);
                    return new Skill(id, name);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Skill updateSkill) {
        Collection<Skill> skills = getAll();
        for (Skill skill : skills
             ) {
            if(skill.getId() == updateSkill.getId()){
                skill.setName(updateSkill.getName());
            }
        }
        saveAll(skills);
    }

    @Override
    public void delete(Long id) {
        Collection<Skill> skills = getAll();
        Skill skill = get(id);
        if(skill!=null){
            skills.remove(skill);
        }
        saveAll(skills);
    }

    @Override
    public Skill get(Long idParam) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line=reader.readLine())!=null){
                String[] lineParams = line.split(",");
                Long id = Long.parseLong(lineParams[0]);
                if(id.equals(idParam)){
                    String name = lineParams[1];
                    return new Skill(id,name);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Skill> getAll() {
        Set<Skill> skills = new HashSet<>();
        try(BufferedReader bufferedReader =new BufferedReader(new FileReader(filename))){
            String line;
            while ((line=bufferedReader.readLine())!=null){
                String[] lineParams = line.split(",");
                long id = Long.parseLong(lineParams[0]);
                String name = lineParams[1];

                skills.add(new Skill(id,name));
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return skills;
    }
}
