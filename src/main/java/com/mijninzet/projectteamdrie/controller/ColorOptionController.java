package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.ColorOption;
import com.mijninzet.projectteamdrie.service.ColorOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColorOptionController {
    @Autowired
    private ColorOptionService colorOptionService;
    @RequestMapping("/Options")
    public List<ColorOption> getAllOptions() {
        return colorOptionService.getAllOptions();
    }
}
