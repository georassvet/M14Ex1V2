package main.java.controllers;

import main.java.models.Account;
import main.java.repository.IAccountRepository;
import main.java.repository.io.AccountRepository;

import java.util.Collection;

public class AccountController {
    private IAccountRepository accountRepository;

    public AccountController(){
        accountRepository = new AccountRepository();
    }
    public void addAccount(Account account){
        accountRepository.add(account);
    }
    public void removeAccount(long id){
        accountRepository.delete(id);
    }
    public void updateDeveloper(Account account){
        accountRepository.update(account);
    }
    public Account getAccount(long id){
        return accountRepository.get(id);
    }
    public Collection<Account> getDevelopers(){
        return  accountRepository.getAll();
    }
}
