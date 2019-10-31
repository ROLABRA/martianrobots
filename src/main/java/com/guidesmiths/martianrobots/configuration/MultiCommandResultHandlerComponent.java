package com.guidesmiths.martianrobots.configuration;

import com.guidesmiths.martianrobots.model.MultiCommandResult;
import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.InputProvider;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "")
public class MultiCommandResultHandlerComponent implements ResultHandler<MultiCommandResult> {
    @Autowired
    private RobotBehaviorService robotBehaviorService;
    //@Autowired
    //private InteractiveShellApplicationRunner interactiveShellApplicationRunner;
    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    public synchronized void read(){
        String sentence = "";
        String accumulatedSentence= "";
        Scanner in = new Scanner(System.in);

        InteractiveShellApplicationRunner.disable(configurableEnvironment);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = "";
            System.out.println();
            line = in.nextLine();
            //in.close();
            System.out.println();
            System.out.println("Line 1: " + line);
            //in = new Scanner(System.in);
            while (!in.hasNextLine()) ;
            line = in.nextLine();
            //line = br.readLine();
            System.out.println();
            System.out.println("Line 2: " + line);
            //System.in.read();
            //line = br.readLine();
            line = in.nextLine();
            in.close();
            System.out.println();
            System.out.println(line);
            line = br.readLine();
            System.out.println();
            System.out.println(line);
            /*
            while((line = br.readLine()) != null) {
                    System.out.println(line);

            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResult(MultiCommandResult result) {
        //read();

    }
}