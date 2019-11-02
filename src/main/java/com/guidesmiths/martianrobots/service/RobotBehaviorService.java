package com.guidesmiths.martianrobots.service;

import org.springframework.stereotype.Service;

public interface RobotBehaviorService {
    Object processCommand(String cmd);
    void reset();
}
