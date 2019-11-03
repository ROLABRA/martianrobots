package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Input;

import static org.assertj.core.api.Assertions.assertThat;

public class MartianRobotsLogicTests extends BaseTest {
    @Test
    void logicTest1(){
        final String bounds = "5 3";
        final String initialPosition = "1 1 E";
        final String movements = "RFRFRFRF";
        final String expectedResult = "1 1 E";

        configureInteractiveMode(true);
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

    @Test
    void logicTest2(){
        final String bounds = "5 3";
        final String initialPosition = "3 2 N";
        final String movements = "FRRFLLFFRRFLL";
        final String expectedResult = "3 3 N LOST";

        configureInteractiveMode(true);
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

        System.out.println(result);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void logicTest3(){
        final String bounds = "5 3";
        final String initialPosition = "0 3 W";
        final String movements = "LLFFFLFLFL";
        final String expectedResult = "3 3 N LOST"; //No previous scent here

        configureInteractiveMode(true);
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

    @Test
    void logicTest4(){
        String bounds = "5 3";

        configureInteractiveMode(true);
        startSimulation();
        insertBounds(bounds);

        // Robot 1
        String initialPosition = "1 1 E";
        final String movements1 = "RFRFRFRF";
        String expectedResult = "1 1 E";

        insertInitialRobotPosition(initialPosition);

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return movements1;
            }
        });

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);

        // Robot 2
        initialPosition = "3 2 N";
        final String movements2 = "FRRFLLFFRRFLL";
        expectedResult = "3 3 N LOST";

        insertInitialRobotPosition(initialPosition);

        result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return movements2;
            }
        });

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);

        // Robot 3
        initialPosition = "0 3 W";
        final String movements3 = "LLFFFLFLFL";
        expectedResult = "2 3 S";

        insertInitialRobotPosition(initialPosition);

        result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return movements3;
            }
        });

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }
}
