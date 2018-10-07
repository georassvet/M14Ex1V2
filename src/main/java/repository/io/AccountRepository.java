package main.java.repository.io;

import main.java.repository.IAccountRepository;
import main.java.models.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccountRepository implements IAccountRepository {

    static final String filename = "account.txt";

    @Override
    public Account add(Account account) {
        try(PrintWriter writer =new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            writer.println(account);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return account ;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account get(Long idParam) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
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
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
         String line;
            while ((line=reader.readLine())!=null){
             String[] lineParams = line.split(",");
             long id = Long.parseLong(lineParams[0]);
             String description = lineParams[1];

             Account account = new Account(id,description);
             accounts.add(account);
         }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }
}
