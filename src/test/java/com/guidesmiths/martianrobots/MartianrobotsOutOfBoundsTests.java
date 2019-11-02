package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.configuration.shell.CustomShell;
import com.guidesmiths.martianrobots.util.validators.Constraints;
import org.jline.utils.AttributedStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.guidesmiths.martianrobots.util.validators.Constraints.BAD_COORDINATES_MSG;
import static com.guidesmiths.martianrobots.util.validators.Constraints.MAX_COORDINATE_VALUE;
import static com.guidesmiths.martianrobots.util.validators.Constraints.MIN_COORDINATE_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MartianrobotsApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class MartianrobotsOutOfBoundsTests{
    @Autowired
    public CustomShell customShell;

    /*@Test
    void robotInitialPositionOutOfBoundsOnTop(){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return Constraints.START_SIMULATION;
            }
        });

        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE) + " " + (MAX_COORDINATE_VALUE);
            }
        });

        String[] result = (String[]) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE + 1) + " " + (MAX_COORDINATE_VALUE + 1);
            }
        });

        System.out.println(result);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        for (String validationError : result){
            assertThat(result).startsWith(BAD_COORDINATES_MSG);
        }
    }

    @Test
    void robotInitialPositionOutOfBoundsOnBottom(){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return Constraints.START_SIMULATION;
            }
        });

        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE) + " " + (MAX_COORDINATE_VALUE);
            }
        });

        String[] result = (String[]) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MIN_COORDINATE_VALUE - 1) + " " + (MIN_COORDINATE_VALUE - 1);
            }
        });

        System.out.println(result);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        for (String validationError : result){
            assertThat(result).startsWith(BAD_COORDINATES_MSG);
        }
    }*/

    @Test
    void planetOutOfBoundsOnTop(){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return Constraints.START_SIMULATION;
            }
        });

        String[] result = (String[]) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE + 1) + " " + (MAX_COORDINATE_VALUE + 1);
            }
        });

        System.out.println(result);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        for (String validationError : result){
            assertThat(result).startsWith(BAD_COORDINATES_MSG);
        }
    }

    @Test
    void planetOutOfBoundsOnBottom(){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return Constraints.START_SIMULATION;
            }
        });

        String[] result = (String[]) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MIN_COORDINATE_VALUE - 1) + " " + (MIN_COORDINATE_VALUE - 1);
            }
        });

        System.out.println(result);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        for (String validationError : result){
            assertThat(result).startsWith(BAD_COORDINATES_MSG);
        }
    }

    @Test
    void testCustomSimpleCommand() {

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "echo Hello";
            }

        });

        assertThat(result).isNotNull();
        assertThat(result).startsWith("Hello");
    }
}