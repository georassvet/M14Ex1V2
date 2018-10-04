package main.java.realizations;

import main.java.interfaces.IAccountRepository;
import main.java.models.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class AccountRepository implements IAccountRepository {

    static final String filename = "account.txt";

    static IAccountRepository getAccountRepository(){
        return new AccountRepository();
    }

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
    public Account get(Long id) {
        Collection<Account> accounts = getAll();
        for (Account account : accounts
             ) {
            if(account.getId()==id){
                return account;
            }
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
