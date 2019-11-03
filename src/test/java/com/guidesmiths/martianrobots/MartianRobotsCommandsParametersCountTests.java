package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Input;

import static com.guidesmiths.martianrobots.util.constraints.Constraints.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MartianRobotsCommandsParametersCountTests extends BaseTest {

    @Test
    void firstSimulationCommandMoreParametersThanRequired(){
        startSimulation();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE) + " " + (MAX_COORDINATE_VALUE) + " extraParameter";
            }
        });


        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(1);
        for (String validationError : results){
            assertThat(validationError).startsWith(NOT_CORRECT_PARAMETERS_COUNT.substring(0, 15));
        }
    }

    @Test
    void firstSimulationCommandLessParametersThanRequired(){
        startSimulation();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE);
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(1);
        for (String validationError : results){
            assertThat(validationError).startsWith(NOT_CORRECT_PARAMETERS_COUNT.substring(0, 15));
        }
    }

    @Test
    void secondSimulationCommandLessParametersThanRequired(){
        startSimulation();
        insertBounds();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return "" + (MIN_COORDINATE_VALUE) + " " + (MIN_COORDINATE_VALUE);
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(1);
        for (String validationError : results){
            assertThat(validationError).startsWith(NOT_CORRECT_PARAMETERS_COUNT.substring(0, 15));
        }
    }

    @Test
    void secondSimulationCommandMoreParametersThanRequired(){
        startSimulation();
        insertBounds();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return "" + (MIN_COORDINATE_VALUE) + " " + (MIN_COORDINATE_VALUE) + " E additionalParameter";
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(1);
        for (String validationError : results){
            assertThat(validationError).startsWith(NOT_CORRECT_PARAMETERS_COUNT.substring(0, 15));
        }
    }

    @Test
    void thirdSimulationCommandMoreParametersThanRequired(){
        startSimulation();
        insertBounds();
        insertInitialRobotPosition();

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return "RLRLFRLR additionalParameter";
            }
        });

        assertThat(result).isNotNull();
        String[] results = result.split("\n");
        assertThat(results).hasSize(1);
        for (String validationError : results){
            assertThat(validationError).startsWith(NOT_CORRECT_PARAMETERS_COUNT.substring(0, 15));
        }
    }

    @Test
    void thirdSimulationCommandLessParametersThanRequired(){
        startSimulation();
        insertBounds();
        insertInitialRobotPosition();

        Object result = (Object) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return "";
            }
        });

        assertThat(result).isNotNull();
    }
}
