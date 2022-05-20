package com.spring.crud.controller;

import com.spring.crud.model.Unit;
import com.spring.crud.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/units")
    public String listUnits(Model model) {
        model.addAttribute("listUnits", unitService.getAllUnits());
        return "units";
    }

    @GetMapping("/newunit")
    public String newUnitForm(Model model) {
        model.addAttribute("unit", new Unit());
        return "unit_form";
    }

    @PostMapping("/units/save")
    public String saveUnit(@ModelAttribute("unit") Unit unit) {
        unitService.saveUnit(unit);
        return "redirect:/units";
    }

    @GetMapping("/units/edit/{id}")
    public String showEditUnitForm(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("unit", unitService.getUnitById(id));
        return "unit_form";
    }

    @GetMapping("/units/delete/{id}")
    public String deleteUnit(@PathVariable(value = "id") Integer id, Model model) {
        this.unitService.deleteUnitById(id);
        return "redirect:/units";
    }

}
