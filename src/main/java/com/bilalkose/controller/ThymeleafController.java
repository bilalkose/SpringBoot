package com.bilalkose.controller;

import com.bilalkose.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    //http://localhost:8080/
    @GetMapping({"/", "index"}) //root bileşeni. {} arasına alırsan opsiyonel seçenekleri belirleyebilirsin
    public String index() {
        return "index";
    }

    /////////////////////////////////////////////////////////////////////////

    //http://localhost:8080/thymeleaf1
//    @GetMapping("/thymeleaf1")
//    @ResponseBody
//    public String getThymeleaf1(){
//        return "merhabalar";
//    }

    @GetMapping("/thymeleaf1")
    //@ResponseBody
    public String getThymeleaf1() {
        return "thymeleaf1"; //html ismiyle aynı
    }


    //Model
    //http://localhost:8080/thymeleaf2
    @GetMapping("/thymeleaf2")
    public String getThymeleaf2Model(Model model) {
        model.addAttribute("key_model1", "Ben modelden geldim!");
        model.addAttribute("key_model2", "Ben Modelden geldim-2");
        return "thymeleaf1";
    }

    //Model birden fazla göndermek
    //http://localhost:8080/thymeleaf3
    @GetMapping("/thymeleaf3")
    public String getThymeleaf3Model(Model model) {
        model.addAttribute("key_model1", "Ben Modelden geldim-1");
        model.addAttribute("key_model2", "Ben Modelden geldim-2");
        return "tyhmeleaf_file/thymeleaf3";
    }

    //http://localhost:8080/thymeleaf4
    @GetMapping("/thymeleaf4")
    public String getThymeleaf4Model(Model model) {
        model.addAttribute("key_model1", "Ben Modelden geldim-1");
        return "thymeleaf4";
    }

    //////////////////////////////////
    //Model object göndermek
    //http://localhost:8080/thymeleaf5
    @GetMapping("/thymeleaf5")
    public String getThymeleaf5ModelObject(Model model) {
        model.addAttribute("key_model1", "text");

        ProductDto productDto = ProductDto.builder()
                .productId(0L)
                .productName("Ürün adı")
                .productPrice(2500)
                .build();

        model.addAttribute("key_model2", productDto); //nesne gönderiyorum

        return "thymeleaf5";
    }

    //Model object List göndermek
    //http://localhost:8080/thymeleaf6
    @GetMapping("/thymeleaf6")
    public String getThymeleaf6ModelObjectList(Model model) {
        model.addAttribute("key_model1", "text");
        List<ProductDto> listem = new ArrayList<>();
        listem.add(ProductDto.builder().productId(1L).productName("Ürün adı1").productPrice(1500).build());
        listem.add(ProductDto.builder().productId(2L).productName("Ürün adı2").productPrice(2500).build());
        listem.add(ProductDto.builder().productId(3L).productName("Ürün adı3").productPrice(3500).build());
        listem.add(ProductDto.builder().productId(4L).productName("Ürün adı4").productPrice(4500).build());


        model.addAttribute("product_liste", listem); //nesne gönderiyorum

        return "thymeleaf6";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //@PathVariable
    //http://localhost:8080/thymeleaf7/4
    @GetMapping({"/thymeleaf7/{id}", "/thymeleaf7"})
    public String getThymeleaf7ModelObject(Model model, @PathVariable(name = "id", required = false) Long id) { //pathvariable name ile mapping deki uri aynı olmalı. //mappingurl i opsiyonel hala getrirsen reqired ı false yap. reqiured false diyerek id yazılmazsa id nin null olduğunu görürsün, hata dönmez.
        if (id != null) {
            model.addAttribute("key_model1", "id: " + id);
        } else {
            model.addAttribute("key_model1", "id: bulunamadı");
        }

        return "thymeleaf7";
    }

    //@RequestParam
    //http://localhost:8080/thymeleaf8?id=4&name=Bilal
    @GetMapping("/thymeleaf8")
    public String getThymeleaf8ModelObject(Model model,

                                           @RequestParam(name = "id", required = false, defaultValue = "0") Long id,
                                           @RequestParam(name = "name") String name) { //reqiured false diyerek id yazılmazsa id nin null olduğunu görürsün, hata dönmez.
        if (id != null) {
            model.addAttribute("key_model1", "id: " + id + " adı: " + name);
        } else {
            model.addAttribute("key_model1", "id: bulunamadı");
        }
        return "thymeleaf8";
    }
}
