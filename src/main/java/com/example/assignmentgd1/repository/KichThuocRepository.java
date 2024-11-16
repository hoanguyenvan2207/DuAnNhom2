package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, Integer> {
    List<KichThuoc> findKichThuocsByMaContainsIgnoreCase(String ma);
}
