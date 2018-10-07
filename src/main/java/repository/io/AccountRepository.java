package main.java.repository.io;

import main.java.repository.IAccountRepository;
import main.java.models.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class AccountRepository implements IAccountRepository {

    static final File file = new File("src\\main\\resources\\account.txt");

    @Override
    public Account save(Account account) {
        try(PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(file,true)))){
            writer.println(account);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return account ;
    }

    @Override
    public void update(Account account) {
        try(Scanner reader =new Scanner(file);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file,true)))){
            while (reader.hasNext()) {
                String fileToString = reader.useDelimiter("\\Z").next();
                String[] lines = fileToString.split("\\r\\n");
                for (int i = 0; i < lines.length ; i++) {
                    String[] lineParams = lines[i].split(",");
                    Long id = Long.parseLong(lineParams[0]);
                    if(id.equals(account.getId())){
                        writer.println(account);
                    }
                    else{
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
        try(Scanner reader =new Scanner(file);
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file,true)))){
            while (reader.hasNext()) {
                String fileToString = reader.useDelimiter("\\Z").next();
                String[] lines =fileToString.split("\\r\\n");

                for (int i = 0; i < lines.length ; i++) {
                    String[] lineParams = lines[i].split(",");
                    Long id = Long.parseLong(lineParams[0]);
                    if (id.equals(lineParams)) {
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
    public Account getById(Long idParam) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine())!=null){
                String[] lineParams = line.split(",");
                Long id = Long.parseLong(lineParams[0]);
                if (id.equals(idParam)){
                    String description =lineParams[1];
                    return new Account(id,description);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Account> getAll() {
        Collection<Account> accounts = new ArrayList<>();
        try(Scanner reader = new Scanner(file)){
            while (reader.hasNext()) {
                String fileToString = reader.useDelimiter("\\Z").next();
                String[] lines = fileToString.split("\\r\\n");
                for (int i = 0; i <lines.length ; i++) {
                    String[] lineParams = lines[i].split(",");
                    long id = Long.parseLong(lineParams[0]);
                    String description = lineParams[1];

                    Account account = new Account(id, description);
                    accounts.add(account);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }
}
