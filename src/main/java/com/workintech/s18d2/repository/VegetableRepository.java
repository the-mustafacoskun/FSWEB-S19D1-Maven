package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
    @Query(value="SELECT v FROM Vegetable v ORDER BY v.price DESC")
    List<Vegetable> getByPriceDesc();
    @Query(value="SELECT v FROM Vegetable v ORDER BY v.price ")
    List<Vegetable> getByPriceAsc();
    @Query(value="SELECT v FROM Vegetable v WHERE v.name ILIKE %:name%")
    List<Vegetable> searchByName(String name);
}
