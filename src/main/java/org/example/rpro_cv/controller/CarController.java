package org.example.rpro_cv.controller;

import org.example.rpro_cv.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    List<String> cars = new ArrayList<String>();

    @GetMapping
    public String list(Model model){
        cars.add(new Car("ABC123", "blue", 20.5f, 3));
        model.addAttribute("cars", cars);
        return "list";
    }
}
