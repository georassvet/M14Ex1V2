package main.java.realizations;

import main.java.interfaces.ISkillRepository;
import main.java.models.Skill;

import java.io.*;
import java.util.*;

public class SkillRepository implements ISkillRepository {

    static final String filename = "skills.txt";

    public static SkillRepository getRepository(){
        return new SkillRepository();
    }

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
    public Skill getSkillByName(String name){
        Collection <Skill> skills = getAll();
        for( Skill skill : skills){
            if(skill.getName().equals(name))
            return skill;
        }
        Skill addedSkill = new Skill(name);
        add(addedSkill);
        return addedSkill;
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
    public Skill get(Long id) {
        Collection<Skill> skills = getAll();
        for (Skill skill: skills
             ) {
            if(skill.getId()==id){
                return skill;
            }
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

    public static void main(String[] args) {
        SkillRepository skillRepository =new SkillRepository();
        //add
        skillRepository.add(new Skill("Java"));
        skillRepository.add(new Skill("C#"));
        skillRepository.add(new Skill("Html"));
        skillRepository.add(new Skill("Html"));
        //get all
        for (Skill skill: skillRepository.getAll()
             ) {
            System.out.println(skill);
        }
        //get id=2
        System.out.println("get id=2 -> "+skillRepository.get(2l));
        // update
        skillRepository.update(new Skill(2, "CSharp"));
        //get all
        for (Skill skill: skillRepository.getAll()
                ) {
            System.out.println(skill);
        }
        System.out.println("delete");
        skillRepository.delete(4l);

        for (Skill skill: skillRepository.getAll()
                ) {
            System.out.println(skill);
        }


        
        
    }
}
