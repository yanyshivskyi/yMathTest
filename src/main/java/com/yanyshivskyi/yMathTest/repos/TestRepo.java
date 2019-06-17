package com.yanyshivskyi.yMathTest.repos;

import com.yanyshivskyi.yMathTest.domain.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TestRepo extends JpaRepository<Test, Long> {
    Page<Test> findAll(Pageable pageable);
    List<Test> findByNameContainingIgnoreCase(String name);
    Page<Test> findByNameContainingIgnoreCase(String name, Pageable pageable);
}