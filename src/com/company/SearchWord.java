package com.company;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Search for words in websites.
 */
public class SearchWord implements CommandExecute {

    /**
     * Hold the URL
     */
    private final String url;
    /**
     * Hold the path
     */
    private final String path;

    /**
     * Constructor creates a new SearchWord
     *
     * @param commandStr An array with the URL and path of the file to read.
     * @throws UnknownCommandException for invalid command
     */
    public SearchWord(String[] commandStr) throws UnknownCommandException {
        try {
            url = commandStr[1];
            path = commandStr[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnknownCommandException("invalid command");
        }
    }

    /**
     * @return String[]
     * @throws IOException If the reading file fails
     */
    public String[] readFileAsString() throws IOException {

        return new String(Files.readAllBytes(Paths.get(path))).split("\\s+");
    }

    /**
     * @param word     Word to search.
     * @param keywords The array to search in.
     * @return true if keywords[] contains word.
     */
    public boolean containsAllWords(String word, String[] keywords) {
        //return Arrays.stream(keywords).allMatch(word::contains);
        for (String k : keywords)
            if (!word.contains(k))
                return false;
        return true;
    }

    /**
     * execute the command
     *
     * @return boolean, true if contains All Words, false else.
     * @throws UnknownCommandException for error and invalid command.
     */
    @Override
    public boolean execute() throws UnknownCommandException {

        ConnectToURL connectToURL = new ConnectToURL();
        Response res = connectToURL.connectURL(url);

        ContentCheck contentCheck = new ContentCheck(new String[]{"0", url, "text/"});

        if (!contentCheck.execute())   //The site doesn't contain text
            return false;

        try {
            Document doc = res.parse();
            String textSite = doc.body().text();
            String[] textFile = readFileAsString();
            return containsAllWords(textSite, textFile);
        } catch (IOException e) {
            throw new UnknownCommandException("error");
        }


    }
}
