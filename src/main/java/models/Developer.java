package main.java.models;

import main.java.repository.io.SkillRepository;

import java.util.HashSet;
import java.util.Set;

public class Developer {

    private static long counter;

    private long id;
    private String name;
    private int age;

    private Account account;
    private Set<Skill> skills;

    public Developer(String name, int age, String description, String[] skills) {
        this.id = ++counter;
        this.name = name;
        this.age = age;
        this.skills = new HashSet<>();

        SkillRepository skillRepository =SkillRepository.getRepository();
        for (String s : skills
             ) {
            this.skills.add(skillRepository.getSkillByName(s));
        }
        this.account =new Account(id,description);
    }

    public Developer(long id, String name, int age,Account account, Set<Skill> skills) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.skills = skills;
        this.account =account;
    }

    public String toFileString(){
        return id +"," + name + "," + age+"," + account.getId() + skillsId();
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", account="+ account +
                ", skills=" + skills +
                '}';
    }
    public String skillsId(){
        String result  = "";
        for (Skill skill:skills
             ) {
            result +=","+skill.getId();
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
