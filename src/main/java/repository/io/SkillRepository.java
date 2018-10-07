package main.java.repository.io;

import main.java.repository.ISkillRepository;
import main.java.models.Skill;

import java.io.*;
import java.util.*;

public class SkillRepository implements ISkillRepository {
    File file = new File("src\\main\\resources\\skills.txt");

    @Override
    public Skill save(Skill skill) {
        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file,true)))){
            printWriter.println(skill);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return skill;
    }
    @Override
    public Skill getSkillByName(String nameParam){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
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
       try(Scanner reader =new Scanner(file);
           PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(file)))
       ){
           while(reader.hasNext()) {
               String fileToString = reader.useDelimiter("\\Z").next();
               String[] lines = fileToString.split("\\r\\n");
               for (int i = 0; i < lines.length; i++) {
                   String[] lineParams = lines[i].split(",");
                   Long id = Long.parseLong(lineParams[0]);
                   if (id.equals(updateSkill.getId())) {
                       lines[i] = updateSkill.toString();
                       break;
                   }
               }
               for (int i = 0; i < lines.length; i++) {
                   writer.println(lines[i]);
               }
           }
       }catch (IOException e){
           System.out.println(e.getMessage());
       }
    }

    @Override
    public void delete(Long idParam) {
        try(Scanner reader = new Scanner(file);
        PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(file)))){
            while (reader.hasNext()) {
                String fileToString = reader.useDelimiter("\\Z").next();
                String[] lines = fileToString.split("\\r\\n");
                for (int i = 0; i < lines.length; i++) {
                    String[] lineParams = lines[i].split(",");
                    Long id = Long.parseLong(lineParams[0]);
                    if (id.equals(idParam)) {
                        continue;
                    } else {
                        writer.println(lines[i]);
                    }
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Skill getById(Long idParam) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
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
        try(Scanner reader =new Scanner(file)){
            while (reader.hasNext()) {
                String fileToString = reader.useDelimiter("\\Z").next();
                String[] lines = fileToString.split("\r\n");
                for (int i = 0; i < lines.length; i++) {
                    String[] lineParams = lines[i].split(",");
                    long id = Long.parseLong(lineParams[0]);
                    String name = lineParams[1];

                    skills.add(new Skill(id, name));
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return skills;
    }
}
