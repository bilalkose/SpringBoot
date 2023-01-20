package com.bilalkose.tutorials.controller;

import com.bilalkose.tutorials.dto.TeacherDto;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class FormController {

    //FORM
    //GetMethod
    //http://localhost:8080/form
    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("cv_teacher",new TeacherDto());
        return "form_post/formvalidation";
    }


    //PostMethod
    //http://localhost:8080/form
    //burada pathvariable, requestparam gibi yapılar kullanabilirim
    @PostMapping("/form")
    public String postForm(@Valid @ModelAttribute("cv_teacher") TeacherDto teacherDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("hata var....");
            System.out.println("hata var....");
            return "form_post/formvalidation";
        }
        log.info("Success " + teacherDto);
        //db kaydetme alanı
        return "form_post/success";
    }


    //@GetMapping
    //@PostMapping
    //@PutMapping
    //@DeleteMapping

    //@ModelAttribute
}
