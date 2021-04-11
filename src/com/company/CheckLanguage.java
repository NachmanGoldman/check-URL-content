package com.company;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Check is the website is in English.
 */
public class CheckLanguage implements CommandExecute {

    /**
     * Hold the URL
     */
    private final String url;
    /**
     * Hold the language
     */
    private final String language;

    /**
     * Constructor creates a new CheckLanguage
     *
     * @param commandStr An array with the URL and the language.
     * @throws UnknownCommandException for invalid command.
     */
    public CheckLanguage(String[] commandStr) throws UnknownCommandException {
        try {
            url = commandStr[1];
            language = commandStr[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnknownCommandException("invalid command");
        }

    }

    /**
     * execute the command
     *
     * @param values An array to sum his values.
     * @return sum of array.
     */
    public double sum(double... values) {
        double result = 0;
        for (double value : values)
            result += value;
        return result;
    }

    /**
     * count the letters in String.
     *
     * @param line to count the letters in.
     * @return frequency of each letter.
     */
    public double[] countLetters(String line) {
        line = line.toUpperCase();
        double[] frequency = new double[26];
        for (char ch : line.toCharArray())
            if ((ch >= 'A' && ch <= 'Z')) {     //Check if the char is in English
                frequency[ch - 'A']++;
            }
        return frequency;
    }

    /**
     * @return boolean, true if the website in English, false else.
     * @throws UnknownCommandException for error and invalid command.
     */
    @Override
    public boolean execute() throws UnknownCommandException {

        if (!language.equals("english"))
            return false;

        ConnectToURL connectToURL = new ConnectToURL();
        Connection.Response res = connectToURL.connectURL(url);

        Document doc = null;
        try {
            doc = res.parse();
        } catch (IOException e) {
            throw new UnknownCommandException("erroe");
        }
        String textSite = doc.body().text();

        double[] userFrequency = countLetters(textSite);
        double sum = sum(userFrequency);

        double[] englishFrequency = {0.0748, 0.0134, 0.0411, 0.0308, 0.1282, 0.0240, 0.0185, 0.0414, 0.0725, 0.0014,
                0.0053, 0.0403, 0.0340, 0.0673, 0.0785, 0.0314, 0.0010, 0.0609, 0.0614, 0.1002, 0.0316, 0.0108, 0.0131, 0.0044, 0.0127, 0.0011};
        double threshold = 0;
        for (int i = 0; i < userFrequency.length; i++) {
            userFrequency[i] /= sum;
            threshold += Math.pow((englishFrequency[i] - userFrequency[i]), 2);
        }

        return threshold < 0.004;
    }
}
