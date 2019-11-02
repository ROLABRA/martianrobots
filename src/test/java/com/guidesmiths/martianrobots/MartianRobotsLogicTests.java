package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Input;

import static org.assertj.core.api.Assertions.assertThat;
//TODO: Finish
/*
public class MartianRobotsLogicTests extends BaseTest {
    @Test
    void thirdSimulationCommandLessParametersThanRequired(){
        final String bounds = "5 3";
        final String initialPosition = "1 1 E";
        final String movements = "RFRFRFRF";
        final String expectedResult = "1 1 E";

        startSimulation();
        insertBounds(bounds);
        insertInitialRobotPosition(initialPosition);

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return movements;
            }
        });

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }
}*/
