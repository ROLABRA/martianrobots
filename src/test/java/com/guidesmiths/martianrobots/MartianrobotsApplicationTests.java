package com.guidesmiths.martianrobots;

import com.guidesmiths.martianrobots.configuration.shell.CustomShell;
import org.jline.utils.AttributedStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.shell.ParameterResolver;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MartianrobotsApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class MartianrobotsApplicationTests {

    @Autowired
    private CustomShell customShell;

	//TODO: Implement this
    /*@Test
    void testCustomMultipleCommand() {

        String result = (String) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "echo Hello";
            }

        });

        assertThat(result).isNotNull();
        assertThat(result).startsWith("Hello");
    }*/

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

    @Test
    void testNativeCommand() {

        AttributedStringBuilder result = (AttributedStringBuilder) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "help";
            }

        });

        assertThat(result).isNotNull();
        assertThat(result.toString()).startsWith("AVAILABLE COMMANDS");
    }
}