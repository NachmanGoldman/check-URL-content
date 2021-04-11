package com.company;

import java.io.IOException;

/**
 * This is an interface to implement the Command Design Pattern
 */
public interface CommandExecute {

    /**
     * The classes that implement this interface should implement execute() function
     *
     * @return boolean This is the answer is the user command works well
     * @throws UnknownCommandException for error and invalid command.
     */
    boolean execute() throws UnknownCommandException;
}
