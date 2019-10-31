package com.guidesmiths.martianrobots.configuration;

import com.guidesmiths.martianrobots.model.MultiCommandResult;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.shell.ResultHandler;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "")
public class TestThrowableResult implements ResultHandler<Throwable> {
    @Override
    public void handleResult(Throwable result) {
        //read();
        System.out.println("HEREEE TestThrowableResult.handleResult");
    }
}
