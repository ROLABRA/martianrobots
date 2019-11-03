package com.guidesmiths.martianrobots.modules.robot;

public interface RobotBehaviorService {
    Object processCommand(String cmd);
    void reset();
}
