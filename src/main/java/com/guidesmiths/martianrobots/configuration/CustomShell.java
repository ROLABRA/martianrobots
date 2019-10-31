package com.guidesmiths.martianrobots.configuration;

import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.*;

import java.io.IOException;
import java.lang.reflect.Field;

public class CustomShell extends Shell {
    @Autowired
    public RobotBehaviorService robotBehaviorService;

    @Autowired
    private LineReader lineReader;

    public CustomShell(ResultHandler resultHandler) {
        super(resultHandler);
    }

    @Override
    public Object evaluate(Input input) {
        System.out.println(lineReader.getTerminal().getType());
            Object result = super.evaluate(input);
            if(result instanceof CommandNotFound){
                System.out.println("Command not found");
                robotBehaviorService.processCommand();
            }

        return "OK";
    }
/*
    @Override
    public void run(InputProvider inputProvider) throws IOException {
        Object result = null;
        while (!(result instanceof ExitRequest)) {
            Input input = null;
            try {
                input = inputProvider.readInput();
            }
            catch (Exception e) {
                try {
                    System.out.println("ERROR in INPUT!");
                    Field f = Shell.class.getDeclaredField("resultHandler");
                    f.setAccessible(true);
                    ResultHandler resultHandler = (ResultHandler)f.get(this);
                    resultHandler.handleResult(e);
                    continue;
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            if (input == null) {
                break;
            }
            result = evaluate(input);
            if (result != NO_INPUT && !(result instanceof ExitRequest)) {
                try {
                    Field f = null;
                    f = Shell.class.getDeclaredField("resultHandler");
                    f.setAccessible(true);
                    ResultHandler resultHandler = (ResultHandler)f.get(this);
                    resultHandler.handleResult(result);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
