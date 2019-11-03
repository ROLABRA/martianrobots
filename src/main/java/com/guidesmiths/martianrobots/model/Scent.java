package com.guidesmiths.martianrobots.model;

import com.guidesmiths.martianrobots.model.basic.Coordinates;
import lombok.Getter;

public class Scent {
    @Getter
    private Coordinates coordinates;

    public Scent(Coordinates coordinates){
        this.coordinates = coordinates;
    }
}
