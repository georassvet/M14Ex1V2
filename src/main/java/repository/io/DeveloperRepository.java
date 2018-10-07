package main.java.repository.io;

import main.java.repository.IDeveloperRepository;
import main.java.models.Account;
import main.java.models.Developer;
import main.java.models.Skill;

import java.io.*;
import java.util.*;

public class DeveloperRepository implements IDeveloperRepository {

    static final File file = new File("src\\main\\resources\\developer.txt");


    @Override
    public Developer save(Developer developer) {
        try(PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
                writer.println(developer.toFileString());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        new AccountRepository().save(developer.getAccount());
        return developer;
    }

    @Override
    public void update(Developer updateDeveloper) {
       try(Scanner reader = new Scanner(file);
       PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(file,true)))){
           while(reader.hasNext()) {
               String fileToString = reader.useDelimiter("\\Z").next();
               String[] lines = fileToString.split("\\r\\n");
               for (int i = 0; i < lines.length; i++) {
                   String[] lineParams = lines[i].split(",");
                   Long id = Long.parseLong(lineParams[0]);
                   if (id.equals(updateDeveloper.getId())) {
                       writer.println(updateDeveloper.toFileString());
                       new AccountRepository().update(updateDeveloper.getAccount());
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
    public void delete(Long idParam) {
        try(Scanner reader = new Scanner(file);
        PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(file,true)))){
            while(reader.hasNext()) {
                String fileToString = reader.useDelimiter("\\Z").next();
                String[] lines = fileToString.split("\\r\\n");
                for (int i = 0; i < lines.length; i++) {
                    String[] lineParams = lines[i].split(",");
                    Long id = Long.parseLong(lineParams[0]);
                    if (id.equals(idParam)) {
                        Long accountId = Long.parseLong(lineParams[3]);
                        new AccountRepository().delete(accountId);
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
    public Developer getById(Long idParam) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line=reader.readLine())!=null){
                String[] lineParams = line.split(",");
                Long id = Long.parseLong(lineParams[0]);
                if(id.equals(idParam)){
                    String name = lineParams[1];
                    int age = Integer.parseInt(lineParams[2]);
                    Account account = new AccountRepository().getById(Long.parseLong(lineParams[3]));
                    Set<Skill> skills = new HashSet<>();
                    SkillRepository skillRepository = new SkillRepository();
                    for (int i = 4; i < lineParams.length ; i++) {
                        skills.add(skillRepository.getById(Long.parseLong(lineParams[i])));
                    }
                    return new Developer(id, name, age, account, skills);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Developer> getAll() {
        Collection<Developer> developers  = new ArrayList();
        try(Scanner reader = new Scanner(file)){
            String fileToString;
           while (reader.hasNext()) {
                fileToString = reader.useDelimiter("\\Z").next();

                String[] lines = fileToString.split("\\r\\n");
                for (int i = 0; i < lines.length; i++) {
                    String[] lineParams = lines[i].split(",");
                    long id = Long.parseLong(lineParams[0]);
                    String name = lineParams[1];
                    int age = Integer.parseInt(lineParams[2]);
                    Account account = new AccountRepository().getById(Long.parseLong(lineParams[3]));
                    Set<Skill> skills = new HashSet<>();
                    SkillRepository skillRepository = new SkillRepository();
                    for (int j = 4; j < lineParams.length; j++) {
                        skills.add(skillRepository.getById(Long.parseLong(lineParams[j])));
                    }
                    developers.add(new Developer(id, name, age, account, skills));
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return developers;
    }
}
