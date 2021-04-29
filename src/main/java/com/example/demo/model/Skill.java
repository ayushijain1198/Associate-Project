package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Skills")
public class Skill {
    @Id
    @Column(name="Skill_Id")
    private Integer skillId;
    @Column(name="skill_title")
    private String skill_title;

    public Skill() {
        super();
    }

    public Skill(Integer skillId, String skill_title) {
        this.skillId = skillId;
        this.skill_title = skill_title;
    }

   public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkill_title() {
        return skill_title;
    }

    public void setSkill_title(String skill_title) {
        this.skill_title = skill_title;
    }
}
