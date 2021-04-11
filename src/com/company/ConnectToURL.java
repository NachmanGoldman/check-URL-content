package com.company;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * A class that other classes use for the Internet Connections
 */
public class ConnectToURL {

    /**
     * @param url A string that holds the URL to the connection
     * @return Connection.Response
     * @throws UnknownCommandException If the internet connection fails or illegal argument in the URL
     */
    public Connection.Response connectURL(String url) throws UnknownCommandException {

        try {
            return Jsoup.connect(url).ignoreContentType(true).execute();
        } catch (IllegalArgumentException e){
            throw new UnknownCommandException("bad url");
        } catch (IOException e) {
            throw new UnknownCommandException("error");
        }
    }
}
