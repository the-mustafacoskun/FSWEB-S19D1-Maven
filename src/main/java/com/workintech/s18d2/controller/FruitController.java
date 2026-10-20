package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.FruitResponse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/fruit")
public class FruitController {

    private final FruitService fruitService;

    @GetMapping
    public List<Fruit> getAllFruits() {
        return fruitService.getByPriceAsc();
    }
    @GetMapping("/desc")
    public List<Fruit> getAllFruitsDesc() {
        return fruitService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public FruitResponse getFruitById(@Positive(message = "id sıfırdan küçük olamaz") @PathVariable Long id) {
        return  new FruitResponse("get by id başarılı",fruitService.getById(id));
    }

    @GetMapping("/name/{name}")
    public List<Fruit> getFruitsByName(@Size(min=2,max=45,message = "Name size must be between 2 to 45") @PathVariable("name") String name) {
        return fruitService.searchByName(name);
    }

    @PostMapping
    public Fruit save(@Validated  @RequestBody Fruit fruit) {
        if(fruitService.getById(fruit.getId()) == null) {
            return  fruitService.save(fruit);
        }else{
            Fruit updatedFruit= fruitService.getById(fruit.getId());
            updatedFruit.setName(fruit.getName());
            updatedFruit.setPrice(fruit.getPrice());
            updatedFruit.setFruitType(fruit.getFruitType());
            return fruitService.save(updatedFruit);
        }

    }
    @DeleteMapping("/{id}")
    public Fruit delete(@Positive @PathVariable Long id) {
        return  fruitService.delete(id);
    }






}
