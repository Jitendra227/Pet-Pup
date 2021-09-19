package com.jitendra.petpup.model.data;

import java.io.Serializable;

public class PupItems implements Serializable {
    private String breeds;

    public PupItems(String breeds) {
        this.breeds = breeds;
    }

    public String getBreeds() {
        return breeds;
    }

    public void setBreeds(String breeds) {
        this.breeds = breeds;
    }
}
