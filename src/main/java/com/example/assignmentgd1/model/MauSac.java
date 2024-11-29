package com.example.assignmentgd1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mau_sac")
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ma;
    private String ten;
    private Boolean trangThai;
<<<<<<< HEAD
=======

    public MauSac(Integer id, Boolean trangThai, String ten, String ma) {
        this.id = id;
        this.trangThai = trangThai;
        this.ten = ten;
        this.ma = ma;
    }
>>>>>>> 544b846 (add unit test mauSac)
}
