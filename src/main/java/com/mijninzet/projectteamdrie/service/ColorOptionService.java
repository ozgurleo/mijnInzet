package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.ColorOption;
import com.mijninzet.projectteamdrie.repository.ColorOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorOptionService {
    @Autowired
    private ColorOptionRepository colorOptionRepository;
    public List<ColorOption>getAllOptions(){
        ArrayList<ColorOption>colorOptions = new ArrayList<>();
        colorOptionRepository.findAll()
                .forEach(colorOptions::add);
        return colorOptions;
    }

}
