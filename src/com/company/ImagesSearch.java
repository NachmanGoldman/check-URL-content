package com.company;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Search for images in website.
 */
public class ImagesSearch implements CommandExecute {

    /**
     * Hold the URL
     */
    private final String url;

    /**
     * Constructor creates a new SearchWord
     *
     * @param commandStr An array with the URL.
     * @throws UnknownCommandException for invalid command.
     */
    public ImagesSearch(String[] commandStr) throws UnknownCommandException {
        try {
            url = commandStr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnknownCommandException("invalid command");
        }
    }

    /**
     * execute the command
     *
     * @return boolean, true if the website contains image, false else.
     * @throws UnknownCommandException for error and invalid command.
     */
    @Override
    public boolean execute() throws UnknownCommandException {

        ConnectToURL connectToURL = new ConnectToURL();
        Connection.Response res = connectToURL.connectURL(url);


        Document doc = null;
        try {
            doc = res.parse();
        } catch (IOException e) {
            throw new UnknownCommandException("error");
        }
        Elements imageElements = doc.select("img");


        return !imageElements.isEmpty();
    }

}
