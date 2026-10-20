package com.workintech.s18d2.dto;

import com.workintech.s18d2.entity.Vegetable;

public record VegetableResponse(String message, Vegetable vegetable) {
}
