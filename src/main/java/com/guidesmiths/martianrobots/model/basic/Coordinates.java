package com.guidesmiths.martianrobots.model.basic;

import com.guidesmiths.martianrobots.util.validators.Constraints;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

import static com.guidesmiths.martianrobots.util.validators.Constraints.BAD_COORDINATES_MSG;

@Validated
public class Coordinates {
    @Min(value = Constraints.MIN_COORDINATE_VALUE, message = BAD_COORDINATES_MSG)
    @Max(value = Constraints.MAX_COORDINATE_VALUE, message = BAD_COORDINATES_MSG)
    @NotNull
    @Getter
    private Integer posX;
    @Min(value = Constraints.MIN_COORDINATE_VALUE, message = BAD_COORDINATES_MSG)
    @Max(value = Constraints.MAX_COORDINATE_VALUE, message = BAD_COORDINATES_MSG)
    @NotNull
    @Getter
    private Integer posY;

    public Coordinates(String posX, String posY){
        this.posX = Integer.valueOf(posX);
        this.posY = Integer.valueOf(posY);
    }

    public void incrementX(){
        this.posX++;
    }
    public void incrementY(){
        this.posY++;
    }
    public void decrementX(){
        this.posX--;
    }
    public void decrementY(){
        this.posY++;
    }
}
