package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Input;

import static com.guidesmiths.martianrobots.util.constraints.Constraints.*;
import static org.assertj.core.api.Assertions.assertThat;

class MartianrobotsOutOfBoundsTests extends BaseTest {
    @Test
    void robotInitialPositionOutOfBoundsOnTop(){
       startSimulation();
       insertBounds();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE + 1) + " " + (MAX_COORDINATE_VALUE + 1) + " E";
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(2);
        for (String validationError : results){
            assertThat(validationError).startsWith(BAD_COORDINATES_MSG);
        }
    }

    @Test
    void robotInitialPositionOutOfBoundsOnBottom(){
        startSimulation();
        insertBounds();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MIN_COORDINATE_VALUE - 1) + " " + (MIN_COORDINATE_VALUE - 1) + " E";
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(2);
        for (String validationError : results){
            assertThat(validationError).startsWith(BAD_COORDINATES_MSG);
        }
    }

    @Test
    void planetOutOfBoundsOnTop(){
        startSimulation();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE + 1) + " " + (MAX_COORDINATE_VALUE + 1);
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(2);
        for (String validationError : results){
            assertThat(validationError).startsWith(BAD_COORDINATES_MSG);
        }
    }

    @Test
    void planetOutOfBoundsOnBottom(){
        startSimulation();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MIN_COORDINATE_VALUE - 1) + " " + (MIN_COORDINATE_VALUE - 1);
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(2);
        for (String validationError : results){
            assertThat(validationError).startsWith(BAD_COORDINATES_MSG);
        }
    }
}