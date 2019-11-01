package com.guidesmiths.martianrobots.service.impl;

import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.springframework.stereotype.Service;

@Service
public class RobotBehaviorServiceImpl implements RobotBehaviorService {
    public void processCommand(String cmd){
        System.out.println("Processing robot command");
        String[] attrs = new String[cmd.split(" ").length - 1];
        System.arraycopy(cmd.split(" "), 1, attrs, 0, cmd.split(" ").length - 1);

        for(String a : attrs)
        System.out.println(a);
    }
}
