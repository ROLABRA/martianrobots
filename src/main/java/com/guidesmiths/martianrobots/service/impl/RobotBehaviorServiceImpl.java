package com.guidesmiths.martianrobots.service.impl;

import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.springframework.stereotype.Service;

@Service
public class RobotBehaviorServiceImpl implements RobotBehaviorService {
    public void processCommand(){
        System.out.println("Processing robot command");
    }
}
