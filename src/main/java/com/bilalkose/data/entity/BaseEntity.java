package com.bilalkose.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass //? - superclass olduğunu göstermek için
@Getter
@Setter

//Auditing
@EntityListeners(AuditingEntityListener.class) // auditing -> kim ne zaman neyi değiştirdi. db'deki created date ve updated date alanlarını güncelliyor
@JsonIgnoreProperties(value = {"created_date, update_date"},allowGetters = true)
public class BaseEntity {
    //birden fazla ortak entity yapıları için baseEntity yapısı kullanırız

    @Id
    @Column(name = "id",nullable = false) //db deki adı, boş geçilemez
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //bütün yapılarda id kesin vardır


    //create ile alakalı ortak işlemler
    @Column(name="created_by")
    @CreatedBy //?
    private String createdBy; //kim oluşturdu?
    @Column(name="created_date")
    @CreatedDate //?
    private Date createdDate; //ne zaman oluşturdu?


    //update ile alakalı işlemler

    @Column(name="update_by")
    @LastModifiedBy //kim tarafında düzenlendi bilgisini buradan alıyoruz
    private String updateBy;

    @Column(name="update_date")
    @LastModifiedDate //ne zaman düzenlendi bilgisini buradan alıyoruz
    private Date updateDate;

    @Column(name = "system_auto_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;
}
