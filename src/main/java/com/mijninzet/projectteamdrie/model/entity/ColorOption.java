package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class ColorOption {
    @Id
    private String optionName;

    public ColorOption() {
        this.optionName="";
    }

    public ColorOption(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
