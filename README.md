# MARTIANROBOTS #

## Description ##
This project implements the required functionality exposed in the _interview code challenge_ that can be consulted [here](https://github.com/guidesmiths/interview-code-challenges/blob/master/java/martian-robots/instructions.md).

To develop the project, it has been used _Spring Shell_ module as core to process the commands as it provides the basics if a common shel utility and suits the project scope: for example, by default we have the next functionality:
* Default commands (as help, clean, etc.)
* Autocomplete facility: Commands can be completed by pressing _TAB_ key
* Historic facility: The user can search for previous commands with directional keys (up and down)

But not every needed functionality is provided by default, so it has been needed to be customized to let the programmer (me), introduce the required interaction, commands processing and required logic..

For example, one important thing that this module doesn't cover is the possibility of process batch commands, and this is something that is part of the core functionality needed (as we will include the parameters by lines, the bounding box, the initial robot position, etc.)

All the needed configuration for this purpose can be found in the _configuration_ package.

## Set up ##

This project includes lombok facility, which requires the _annotation processing_ to be activated.
* In IntelliJ this can be done pressing 'Ctrl'+'Alt'+'s' -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable Annotation Processing

An In-IDE facility has also been provided to be able to test the application easily. To launch it from IntelliJ, press right-click in the _MartianrobotsApplication_ file and click on _Debug 'MartianrobotsA...main()'. The application starts and can be now be tested from the IDE console.

## Details ##
### Specs ###
This proect has been implemented by using the following technologies:
* Java 8 (build 1.8.0_161-b12)
* Spring shell v2.0.0
* Lombok v1.16.18

### Available profiles ###
Two profiles have been configured:
* mars: This is the profile used when generating the jar file and when running application in the IDE. A property has been set (_spring.profiles.active_) just for demonstration purposes on the usage. The content of this variable is shown in the console greeting message, indicating the planet where the robots are supposed to be.
* test: This is the profile used when executing unitary tests. In this case, the @Profile annotation has been used in the code (com.guidesmiths.martianrobots.configuration.shell.JLineShellConfiguration.java, interactiveApplicationRunner method) to avoid the console to start the interactive mode, as it enters in an infinite loop waiting for user input.

### DOCKER ###
A small Dockerfile has been included to be able to run the application in a docker container, just for demonstration purposes.
To run the application in a docker container:
1. Check that the jar file has been generated (if not, execute the bootJar gradle task)
2. Open shell in the project root /martianrobots (where the Docker file is placed)
3. Execute docker build & run commands:
```
docker build -t martianrobots .
docker run -it martianrobots
```
NOTE: The build process lasts several minutes, as JDK 8 is installed with the same distribution sed to develop the project.

### Standalone execution ###
Apart from the docker file provided, the project can simply be compiled, built and packaged, and then used from any console (having the java version needed installed and the environment properly configured). To launch the application this way, just follow the next steps:
1. Check that the jar file has been generated (if not, execute the bootJar gradle task)
2. Open a command line where the .jar file is placed (by default it is generated at /martianrobots/build/libs)
3. Exec the following command:
```
java -jar {jarfile}
```

### USAGE ###
To start working with the shell, once launched a greeting message will appear:
```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.0.RELEASE)


Welcome to MARS!. This is your martianrobots console. Type "help" if you need some tips to use it.

martianrobots$>
```
As indicated by the tool, we can type _help_ and press enter. A commands leyend is shown:

```
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        script: Read and execute commands from a file.

Shell Commands Controller
        echo: Returns the parameter provided. i.e:
                        $>echo "Hello there"
                        Hello there
        interactive_output: Usage: interactive_output [on|off|get]. Set the preference of interactive output. When interactive output is set to 'on', the robot position is shown in each iteration. Otherwise the robots final position is shown at the end.
        start_simulation: Starts martian robot simulation. Type 'end' to finish execution (and continue executing the console) or type "exit" or "quit" to exit console.


martianrobots$>
```

So these are the commands available. As indicated, we can type _start_simulation_ and start entering coordinates:

```
martianrobots$>start_simulation
50 50
2 3 E
RFRFLRF
end
0 2 W

martianrobots$>
```

### Unitary tests ###
Unitary tests have been also provided and can be executed by running the gradle _test_ task.

### FUTURE IMPROVEMENTS ###
There are some other functionalities that were planned to add to the project but it has been impossible due to the lack of time. At lease, they have been listed here to have them into account in a possible future release/improvement.
#### Internationalization ####
Most projects nowadays are abailable in several languages, and this is something that can be added to the project and provide the user a command to list and switch between the available languages.
#### Documentation ####
Even that on one hand 'a good code is not necessary to be commented', on the other hand it's a good practice to document the code. For this there are several options, like Swagger (more intended) for the API REST or javadoc.
#### GUI mode ####
Another improvement that was intended to be added to the project is a GUI mode. With this mode active, a grid was going to be shown to the user in order to visualize the robots position. This could also be configurable to show it after each robot ends its movements or just at the end of the simulation. This way, a accumulated robot and scents positions could be s¡visualized by the user, making the tool more user-friendly.

Visualization example:
```
_________________
|___|___|___|___|
|___|_r_|___|_x_|
|___|___|___|___|
|_r_|___|___|___|
|___|___|_x_|___|
```
Where:
r -> Robot
x -> Scent
#### Shell customization ####
Other functionality that was intended to be added is to let the user to configure colors for the input
#### Repository contribution ####
On one hand, the used library _Spring Shell_, has several advantages as are core functionality and default commands and utilities. On the other hand, it has some limitations when working with it, as for example the lack of multicommand utility. For this reason, some improvements could be done in the library source code and to make a pull request to provide that functionality.
#### Code checking ####
It's a good practice to make continuous code checking with tools like Sonar. This helps to avoid code smells and in some cases critical code bugs or performance problems.
#### AWS database ####
It was intended to provide an AWS (cloud) database (i.e. mysql) to store the user preferences instead of using a local one; this way we could have real persistence but due to the lack of time and for demonstration purposes I've thought that the h2 database is enough.
