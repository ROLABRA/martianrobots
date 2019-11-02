package com.guidesmiths.martianrobots.model;

import com.guidesmiths.martianrobots.annotations.ValidEnum;
import com.guidesmiths.martianrobots.model.basic.Coordinates;
import javafx.geometry.BoundingBox;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Validated
@Scope(scopeName = SCOPE_PROTOTYPE)
@Component
public class Robot {
    private static BoundingBox bounds;
    private static List<Scent> scents = new ArrayList<Scent>();

    private Coordinates coordinates;
    private ORIENTATION orientation;

    private boolean lost = false;

    private enum ORIENTATION{
        N,
        S,
        E,
        W
    }

    private enum MOVEMENTS{
        F,
        R,
        L
    }

    public Robot(){

    }
    //TODO: Control exception here if params not valid
    //TODO: Validate orientation type

    public void init(@Valid Coordinates coordinates, @ValidEnum(targetClassType = ORIENTATION.class, message = "Invalid orientation. Must be one of: 'N', 'S', 'E', 'W'") String orientation){
        this.coordinates = coordinates;
        this.orientation = ORIENTATION.valueOf(orientation);
    }

    public void move(@ValidEnum(targetClassType = MOVEMENTS.class, message = "Invalid movement. Must be one of: 'F', 'R', 'L'") String step){
        switch (MOVEMENTS.valueOf(step)) {
            case F:
                moveFroward();

                break;
            case L:
                moveLeft();

                break;
            case R:
                moveRight();

                break;
            default:
                throw new RuntimeException("Movement " + step + " not allowed");
            }
    }

    //This method has to be called from class instance to enforce parameters validation
    public void setBounds(@Valid Coordinates bounds){
        Robot.bounds = new BoundingBox(0, 0, Double.valueOf(bounds.getPosX()),Double.valueOf(bounds.getPosY()));
    }

    private void moveFroward(){
        if(lost) return;

        boolean avoidBeingLost = false;
        for(Scent scent: scents){
            if(scent.getCoordinates().getPosX() == coordinates.getPosX() && scent.getCoordinates().getPosY() == coordinates.getPosY()){
                avoidBeingLost = true;
                break;
            }
        }

        switch (orientation){
            case N:
                if(coordinates.getPosY() + 1 > bounds.getMaxY()){
                    if(avoidBeingLost) {
                        return;
                    }else{
                        scents.add(new Scent(coordinates));
                    }
                }
                coordinates.incrementY();

                break;
            case S:
                if(coordinates.getPosY() - 1 < bounds.getMinY()){
                    if(avoidBeingLost) {
                        return;
                    }else{
                        scents.add(new Scent(coordinates));
                    }
                }
                coordinates.decrementY();

                break;
            case E:
                if(coordinates.getPosX() + 1 > bounds.getMaxX()){
                    if(avoidBeingLost) {
                        return;
                    }else{
                        scents.add(new Scent(coordinates));
                    }
                }
                coordinates.incrementX();

                break;
            case W:
                if(coordinates.getPosX() - 1 < bounds.getMinX()){
                    if(avoidBeingLost) {
                        return;
                    }else{
                        scents.add(new Scent(coordinates));
                    }
                }
                coordinates.decrementX();

                break;
            default:
                throw new RuntimeException("Turning " + orientation + " not allowed");
        }

        lost = coordinates.getPosX() > bounds.getMaxX() || coordinates.getPosX() < bounds.getMinX() || coordinates.getPosY() > bounds.getMaxY() || coordinates.getPosY() < bounds.getMinY();
    }

    private void moveLeft(){
        switch (orientation){
            case N:
                orientation = ORIENTATION.W;

                break;
            case S:
                orientation = ORIENTATION.E;

                break;
            case E:
                orientation = ORIENTATION.N;

                break;
            case W:
                orientation = ORIENTATION.S;

                break;
            default:
                throw new RuntimeException("Turning " + orientation + " not allowed");
        }
    }

    private void moveRight(){
        switch (orientation){
            case N:
                orientation = ORIENTATION.E;

                break;
            case S:
                orientation = ORIENTATION.W;

                break;
            case E:
                orientation = ORIENTATION.S;

                break;
            case W:
                orientation = ORIENTATION.N;

                break;
            default:
                throw new RuntimeException("Turning " + orientation + " not allowed");
        }
    }


    public String toString(){
        return lost?"LOST":coordinates.getPosX() + " " + coordinates.getPosY() + " " + orientation;
    }
}
