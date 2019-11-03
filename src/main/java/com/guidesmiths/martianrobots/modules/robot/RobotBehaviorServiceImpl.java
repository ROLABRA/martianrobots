package com.guidesmiths.martianrobots.modules.robot;

import com.guidesmiths.martianrobots.model.Robot;
import com.guidesmiths.martianrobots.model.basic.Coordinates;
import com.guidesmiths.martianrobots.modules.shell.multiexecution.MultiStepExecutionService;
import com.guidesmiths.martianrobots.modules.shell.preferences.ShellPreferencesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

import static com.guidesmiths.martianrobots.util.constraints.Constraints.FINISH_SIMULATION_COMMAND;
import static com.guidesmiths.martianrobots.util.constraints.Constraints.NOT_CORRECT_PARAMETERS_COUNT;
import static com.guidesmiths.martianrobots.util.constraints.Constraints.PREFERENCE_INTERACTIVE_OUTPUT_KEY;

@Service
public class RobotBehaviorServiceImpl implements RobotBehaviorService {
    private final Logger LOG = Logger.getLogger(RobotBehaviorServiceImpl.class);

    @Autowired
    private MultiStepExecutionService multiStepExecutionService;
    @Autowired
    private ShellPreferencesService shellPreferencesService;
    @Autowired
    private ApplicationContext context;

    private Robot robot;
    private List<Robot> robots = new ArrayList<Robot>();

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

        if(params.length == 1 && FINISH_SIMULATION_COMMAND.equalsIgnoreCase(params[0])){
            String result = null;
            if(!Boolean.valueOf(shellPreferencesService.getPreference(PREFERENCE_INTERACTIVE_OUTPUT_KEY))){
                result = formatOutputResult();
            }

            multiStepExecutionService.setMultiStepExecutionInProcess(false);

            return result;
        }

        if(!validateParamsCount(params)) return new String(String.format(NOT_CORRECT_PARAMETERS_COUNT, neededParams.get(state), params.length));

        try {
            switch (state) {
                case INIT_INSERT_MAX_COORDINATES:
                    robot = ((Robot) context.getBean("robot"));
                    robot.reset();
                    robot.setBounds(new Coordinates(params[0], params[1]));

                    state = MACHINE_STATES.INSERT_INITIAL_POSITION;

                    break;
                case INSERT_INITIAL_POSITION:
                    robot = ((Robot) context.getBean("robot"));
                    robot.init(new Coordinates(params[0], params[1]), params[2]);

                    state = MACHINE_STATES.INSERT_MOVEMENTS;

                    break;
                case INSERT_MOVEMENTS:
                    robot.move(params[0]);

                    state = MACHINE_STATES.INSERT_INITIAL_POSITION;

                    if(Boolean.valueOf(shellPreferencesService.getPreference(PREFERENCE_INTERACTIVE_OUTPUT_KEY))) {
                        return robot.toString();
                    }else{
                        robots.add(robot);
                    }
                    break;
                default:
                    System.out.println("BAD parameters provided");

                    break;
            }
        }catch(ConstraintViolationException e){
            String result = "";
            for(ConstraintViolation c : e.getConstraintViolations()){
                result = result + c.getMessage() + "\n";
            }
            return result;
        }
        return null;
    }

    private String formatOutputResult(){
        String result = "";

        for(Robot robot : robots){
            result = result + robot.toString() + "\n";
        }

        return result;
    }

    public void reset(){
        this.state = MACHINE_STATES.INIT_INSERT_MAX_COORDINATES;
        this.robots = new ArrayList<Robot>();
    }

    private boolean validateParamsCount(String[] params){
        Integer neededParamsInt = neededParams.get(state);
        return neededParamsInt.equals(params.length);
    }
}
