package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.FruitResponse;
import com.workintech.s18d2.dto.VegetableResponse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.FruitService;
import com.workintech.s18d2.services.VegetableService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vegetable")
public class VegetableController {

    private final VegetableService vegetableService;

    @GetMapping
    public List<Vegetable> getAllFruits() {
        return vegetableService.getByPriceAsc();
    }
    @GetMapping("/desc")
    public List<Vegetable> getAllFruitsDesc() {
        return vegetableService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public VegetableResponse getFruitById(@Positive(message = "id sıfırdan küçük olamaz") @PathVariable Long id) {
        return  new VegetableResponse("get by id başarılı",vegetableService.getById(id));
    }

    @GetMapping("/name/{name}")
    public List<Vegetable> getFruitsByName(@Size(min=2,max=45,message = "Name size must be between 2 to 45") @PathVariable("name") String name) {
        return vegetableService.searchByName(name);
    }

    @PostMapping
    public Vegetable save(@Validated @RequestBody Vegetable vegetable) {
        if(vegetableService.getById(vegetable.getId()) == null) {
            return  vegetableService.save(vegetable);
        }else{
            Vegetable updatedVegetable= vegetableService.getById(vegetable.getId());
            updatedVegetable.setName(vegetable.getName());
            updatedVegetable.setPrice(vegetable.getPrice());
            updatedVegetable.setGrownOnTree(vegetable.isGrownOnTree());
            return vegetableService.save(updatedVegetable);
        }

    }
    @DeleteMapping("/{id}")
    public Vegetable delete(@Positive @PathVariable Long id) {
        return  vegetableService.delete(id);
    }
}
