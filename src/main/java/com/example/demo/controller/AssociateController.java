package com.example.demo.controller;

import com.example.demo.model.Associate;
import com.example.demo.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/associates")
public class AssociateController {
    private AssociateService associateService;

    @Autowired
    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }




    @GetMapping("/list")
    public String getAssociates(Model model, String keyword)
    {
        List<Associate> list=associateService.getAllAssociates();
        if(keyword!=null){
            model.addAttribute("associates", associateService.findByKeyword(keyword));
        }
        else {
            model.addAttribute("associates", list);
        }
        return "list-associates";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
      Associate theAssociate = new Associate();
        theModel.addAttribute("associates", theAssociate);
        return "associate-form";
    }
    @PostMapping("/save")
    public String saveAssociate(@ModelAttribute("associates") Associate theAssociate) {

        associateService.createAssociate(theAssociate);
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

