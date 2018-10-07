package main.java.repository;

import main.java.models.Skill;

public interface ISkillRepository extends IRepository<Skill,Long> {
    Skill getSkillByName(String name);
}
