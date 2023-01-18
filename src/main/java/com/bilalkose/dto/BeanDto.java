package com.bilalkose.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class BeanDto {
    private Long id;
    private String beanName;
    private String beanData;

    //başlangıç
    public void initialBeanMethod(){
        log.info("bean başlamadan önce ben varım!"); //bean'de hazır olarak gelmesi gereken bazı bileşenler varsa önce bunu çalıştırmalıyız ki onlar gelsin
        System.out.println("Bean başlamadan önce çalışacak metot. ");

    }

    //bitiş
    public void destroyBeanMethod(){
        log.info("bean bittikten sonra ben varım!"); //bean öldükten sonra ne olacak onu gösterir
        System.out.println("Bean bittikten sonra çalışacak metot. ");
    }
}
