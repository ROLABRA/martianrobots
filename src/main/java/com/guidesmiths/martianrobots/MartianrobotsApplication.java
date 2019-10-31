package com.guidesmiths.martianrobots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.result.ResultHandlerConfig;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAutoConfiguration(exclude = {SpringShellAutoConfiguration.class, StandardCommandsAutoConfiguration.class, JLineShellAutoConfiguration.class})
/*@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=org.springframework.shell.result.ResultHandlerConfig.class)
})*/
@ComponentScan(basePackages = {"com.guidesmiths.martianrobots", "org.springframework.shell.result"},
		excludeFilters = {@ComponentScan.Filter(
				type = FilterType.ASSIGNABLE_TYPE,
				classes = {ResultHandlerConfig.class})
		})
public class MartianrobotsApplication{

	public static void main(String[] args) {
		SpringApplication.run(MartianrobotsApplication.class, args);
	}

}
