package com.example.assignmentgd1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Vui lòng nhập tên khách hàng!")
    private String ten;

    @NotBlank(message = "Vui lòng nhập số điện thoại!")
    private String sdt;

    @NotBlank(message = "Vui lòng nhập mã khách hàng!")
    private String maKh;

    @NotNull(message = "Vui lòng chọn trạng thái!")
    private Boolean trangThai;

}
