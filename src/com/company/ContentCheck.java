package com.company;

import org.jsoup.Connection;

/**
 * Check the content type of website.
 */
public class ContentCheck implements CommandExecute {

    /**
     * Hold the URL
     */
    private final String url;
    /**
     * Hold the content type
     */
    private final String contentType;

    /**
     * Constructor creates a new ContentCheck
     *
     * @param commandStr An array with the URL and the content type.
     * @throws UnknownCommandException for invalid command.
     */
    public ContentCheck(String[] commandStr) throws UnknownCommandException {
        try {
            url = commandStr[1];
            contentType = commandStr[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnknownCommandException("invalid command");
        }
    }

    /**
     * execute the command
     *
     * @return boolean, true if the website content start with string, false else.
     * @throws UnknownCommandException for error and invalid command.
     */
    @Override
    public boolean execute() throws UnknownCommandException {

        ConnectToURL connectToURL = new ConnectToURL();
        Connection.Response res = connectToURL.connectURL(url);

        return res.contentType().startsWith(contentType);

    }
}
