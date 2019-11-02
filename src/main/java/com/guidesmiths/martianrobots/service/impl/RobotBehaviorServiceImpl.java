package com.guidesmiths.martianrobots.service.impl;

import com.guidesmiths.martianrobots.model.Robot;
import com.guidesmiths.martianrobots.model.basic.Coordinates;
import com.guidesmiths.martianrobots.service.MultiStepExecutionService;
import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

import static com.guidesmiths.martianrobots.util.validators.Constraints.NOT_CORRECT_PARAMETERS_COUNT;

@Service
public class RobotBehaviorServiceImpl implements RobotBehaviorService {
    private final Logger LOG = Logger.getLogger(RobotBehaviorServiceImpl.class);

    @Autowired
    private MultiStepExecutionService multiStepExecutionService;
    @Autowired
    private ApplicationContext context;

    private Robot robot;

    private enum MACHINE_STATES {
        INIT_INSERT_MAX_COORDINATES,
        INSERT_INITIAL_POSITION,
        INSERT_MOVEMENTS
    };
    private Map<MACHINE_STATES, Integer> neededParams = new HashMap<MACHINE_STATES, Integer>(){
        {
            put(MACHINE_STATES.INIT_INSERT_MAX_COORDINATES, 2);
            put(MACHINE_STATES.INSERT_INITIAL_POSITION, 3);
            put(MACHINE_STATES.INSERT_MOVEMENTS, 1);
        }
    };
    private MACHINE_STATES state = MACHINE_STATES.INIT_INSERT_MAX_COORDINATES;

    public Object processCommand(String cmd){
        String[] params = cmd.split(" ");

        //TODO: Maybe throw exception
        if(!validateParamsCount(params)) return new String[]{ (String.format(NOT_CORRECT_PARAMETERS_COUNT, neededParams.get(state), params.length))};

        try {
            switch (state) {
                case INIT_INSERT_MAX_COORDINATES:
                    ((Robot) context.getBean("robot")).setBounds(new Coordinates(params[0], params[1]));

                    state = MACHINE_STATES.INSERT_INITIAL_POSITION;

                    break;
                case INSERT_INITIAL_POSITION:
                    robot = ((Robot) context.getBean("robot"));
                    robot.init(new Coordinates(params[0], params[1]), params[2]);

                    state = MACHINE_STATES.INSERT_MOVEMENTS;

                    break;
                case INSERT_MOVEMENTS:
                    String[] steps = params[0].split("");
                    for(String step : steps) {
                        robot.move(step);
                    }

                    state = MACHINE_STATES.INSERT_INITIAL_POSITION;

                    System.out.println(robot.toString());
                    break;
                default:
                    System.out.println("BAD parameters provided");

                    break;
            }
        }catch(ConstraintViolationException e){
            String[] result = new String[e.getConstraintViolations().size()];
            int i = 0;
            for(ConstraintViolation c : e.getConstraintViolations()){
                result[i] = c.getMessage();
                i++;
                //System.out.println(c.getMessage());
            }
            return result;
        }
        return null;
    }

    public void reset(){
        this.state = MACHINE_STATES.INIT_INSERT_MAX_COORDINATES;

    }

    //TODO: CHECK VALID LETTERS/NUMBERS IN INPUT?
    //TODO: ADD INTERACTIVE MODE: The output is shown in each inserted pais of commands or at the end.

    private boolean validateParamsCount(String[] params){
        Integer neededParamsInt = neededParams.get(state);
        return neededParamsInt.equals(params.length);
    }
}
