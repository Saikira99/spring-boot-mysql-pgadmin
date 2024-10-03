package com.example.spring_boot_mysql_pgadmin.entity;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "technology_id")
    private Long id;

    @Column(name = "technology_name")
    private String name;

    @Column(name = "created_date", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updatedDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDate.now();
        updatedDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDate.now();
    }


}
