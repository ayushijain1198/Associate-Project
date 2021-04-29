package com.example.demo.controller;

import com.example.demo.model.Associate;
import com.example.demo.model.Skill;
import com.example.demo.repo.AssociateRepo;
import com.example.demo.repo.SkillRepository;
import com.example.demo.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/associates")
public class AssociateController {
    private AssociateRepo associateRepo;
    private SkillRepository skillRepository;
   private AssociateService associateService;

    @Autowired
    public AssociateController(AssociateRepo associateRepo, SkillRepository skillRepository, AssociateService associateService) {
        this.associateRepo = associateRepo;
        this.skillRepository = skillRepository;
        this.associateService = associateService;
    }

    @RequestMapping("/view/{id}")
    public String developer(@PathVariable Integer associateId, Model model) {
        model.addAttribute("associate", associateService.findById(associateId));
        //model.addAttribute("skills", skillRepository.findAll());
        return "list-associates";
    }


    @GetMapping("/list")
    public String getAssociates(Model model, String keyword)
    {
        List<Associate> list=associateService.getAllAssociates();
        //List<Skill> list1 = skillRepository.findAll();
        if(keyword!=null){
            model.addAttribute("associates", associateService.findByKeyword(keyword));
        }
        else {
            model.addAttribute("associates", list);
            //model.addAttribute("skill",list1);
        }
        return "list-associates";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Associate theAssociate = new Associate();
        Skill theSkill=new Skill();
        theModel.addAttribute("associates", theAssociate);
        theModel.addAttribute("skills",theSkill);
        return "associate-form";
    }

    /*@GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam String name, @RequestParam String email, @RequestParam String mobile, @RequestParam Integer associateId, Model theModel) {
      Associate theAssociate = new Associate();
      theAssociate.setAssociateId(associateId);
      theAssociate.setName(name);
      theAssociate.setEmail(email);
      theAssociate.setMobileNumber(mobile);
      theAssociate.setSkills(skillRepository.findAll());
        theModel.addAttribute("associates", theAssociate);
        theModel.addAttribute("skill",skillRepository.findAll());
        return "associate-form";
    }*/

    @PostMapping("/save/skills")
    public String saveAssociate(@ModelAttribute("associates") Associate theAssociate, @ModelAttribute Skill theSkill) {

        associateService.createAssociate(theAssociate);
        skillRepository.save(theSkill);
        return "redirect:/associates/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("associateId") int theId,
                                    Model theModel) {

       Associate theAssociate = associateService.findById(theId);

        theModel.addAttribute("associates", theAssociate);

        return "associate-form";
    }

    @RequestMapping("/delete")
    public String deleteAssociateById(@RequestParam("associateId") int theId){
        associateService.deleteAssociateById(theId);
        return "redirect:/associates/list";
    }


}

