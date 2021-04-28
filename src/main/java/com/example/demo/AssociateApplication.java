package com.example.demo;

import com.example.demo.model.Associate;
import com.example.demo.model.Skill;
import com.example.demo.repo.AssociateRepo;
import com.example.demo.repo.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jnlp.ClipboardService;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class AssociateApplication implements CommandLineRunner {
    private AssociateRepo associateRepo;
    private SkillRepository skillRepository;

    @Autowired
    public AssociateApplication(AssociateRepo associateRepo, SkillRepository skillRepository) {
        this.associateRepo = associateRepo;
        this.skillRepository = skillRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(AssociateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Skill javascript = new Skill(1, "Javascript");
        Skill java = new Skill(2, "Java");
        Skill cpp = new Skill(3, "C++");
        Skill python = new Skill(4, "Python");

        skillRepository.save(javascript);
        skillRepository.save(java);
        skillRepository.save(cpp);
        skillRepository.save(python);


        associateRepo.save(new Associate(1,"Ayushi","ayushi@email.com", "7415686617", Arrays.asList(new Skill[]{java, python})));
        associateRepo.save(new Associate(2,"John","john@email.com", "8871178818", Arrays.asList(new Skill[]{cpp, python})));
        associateRepo.save(new Associate(3,"Marry","public@email.com", "973682929", Arrays.asList(new Skill[]{java, javascript})));
        associateRepo.save(new Associate(4,"Sachin","sachin@email.com", "9425159540", Arrays.asList(new Skill[]{cpp, javascript, java})));
    }
}
