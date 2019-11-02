package com.guidesmiths.martianrobots.base;

import com.guidesmiths.martianrobots.MartianrobotsApplication;
import com.guidesmiths.martianrobots.configuration.shell.CustomShell;
import com.guidesmiths.martianrobots.service.MultiStepExecutionService;
import com.guidesmiths.martianrobots.util.validators.Constraints;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.guidesmiths.martianrobots.util.validators.Constraints.MAX_COORDINATE_VALUE;
import static com.guidesmiths.martianrobots.util.validators.Constraints.MIN_COORDINATE_VALUE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MartianrobotsApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
public class BaseTest {
    @Autowired
    public CustomShell customShell;
    @Autowired
    public MultiStepExecutionService multiStepExecutionService;

    public void startSimulation(){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return Constraints.START_SIMULATION;
            }
        });
    }

    public void insertBounds(){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return "" + (MAX_COORDINATE_VALUE) + " " + (MAX_COORDINATE_VALUE);
            }
        });
    }

    public void insertBounds(String bounds){
        customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                return bounds;
            }
        });
    }

    public void insertInitialRobotPosition(){
        String[] result = (String[]) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return "" + (MIN_COORDINATE_VALUE) + " " + (MIN_COORDINATE_VALUE) + " N";
            }
        });
    }
    public void insertInitialRobotPosition(String initialPosition){
        String[] result = (String[]) customShell.evaluate(new Input(){
            @Override
            public String rawText() {
                //Orientation param left
                return initialPosition;
            }
        });
    }
}
