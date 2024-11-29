package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
<<<<<<< HEAD
    List<MauSac> findMauSacsByMaContainsIgnoreCase(String ma);
=======


    List<MauSac>findMauSacsByTen(String ten);
    List<MauSac> findMauSacsByMaContainsIgnoreCase(String ma);
    List<MauSac> findMauSacsByID(Integer id);
>>>>>>> 544b846 (add unit test mauSac)
}
