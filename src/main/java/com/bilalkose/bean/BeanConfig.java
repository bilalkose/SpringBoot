package com.bilalkose.bean;

import com.bilalkose.dto.BeanDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {

    //bu dto yu bean configürasyon olarak göstermek için bean annotation ile göstermeliyim sonra configuration yapısıs olarak göstermeliyim

    //bundan sonra bana hazır bir şekilde gelecek bu sınıf. projede hazır bileşen istiyorsam bu annotasyonlarla birlikte ben bean yapısı olusturabilirim
    @Bean(initMethod = "initialBeanMethod",destroyMethod = "destroyBeanMethod")
    @Scope("singleton") //sadece 1 tane.
//    @Scope("request") //sadece istek boyunca
//    @Scope("session") //sadece oturum boyunca
    public BeanDto beanDto(){
        return BeanDto.builder().id(0L).beanName("bean adi").beanData("bean data").build();
    }
}
