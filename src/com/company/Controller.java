package com.company;

import java.io.IOException;
import java.util.Scanner;


/**
 * The controller manages the program.
 */
public class Controller {

    /**
     * Get input from user and send to the CheckInput function,
     * From it gets an object that implements the CommandExecute interface,
     * Executes it and handles program anomalies.
     */
    public void run() {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        while (true) {                           //The loop executes until input is 'q', and this case is handled by CheckInput function
            String input = getString(myObj);

            try {
                CommandExecute c = checkCommand(input.split(" "));      //Factory Design Pattern
                System.out.println(c.execute());                          //execute specific command and print the boolean answer

            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param myObj A Scanner Object to read lines from Console.
     * @return String The input from user.
     */
    private String getString(Scanner myObj) {
        String input = myObj.nextLine();  // Read user command
        return input;
    }

    /**
     * @param commandStr An array String to check which command is, and send it to constructors.
     * @return CommandExecute An object that implements his interface.
     * @throws UnknownCommandException A class that I created for unknown command.
     */
    private CommandExecute checkCommand(String[] commandStr) throws UnknownCommandException {

        switch (commandStr[0]) {
            case "q":
                System.exit(0);              //if input is 'q' exit
            case "t":
                return new ContentCheck(commandStr);
            case "w":
                return new SearchWord(commandStr);
            case "i":
                return new ImagesSearch(commandStr);
            case "l":
                return new CheckLanguage(commandStr);
            default:
                throw new UnknownCommandException("invalid command");
        }
    }
}
