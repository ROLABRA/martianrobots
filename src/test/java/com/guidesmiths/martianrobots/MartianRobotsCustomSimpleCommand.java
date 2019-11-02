package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Input;

import static org.assertj.core.api.Assertions.assertThat;

public class MartianRobotsCustomSimpleCommand extends BaseTest {
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
