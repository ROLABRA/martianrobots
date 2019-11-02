package com.guidesmiths.martianrobots.model;

import com.guidesmiths.martianrobots.model.basic.Coordinates;
import lombok.Getter;

import javax.validation.Valid;

public class Scent {
    @Getter
    private Coordinates coordinates;

    //TODO: CHeck @Valid annotation here
    public Scent(@Valid Coordinates coordinates){
        this.coordinates = coordinates;
    }
}
