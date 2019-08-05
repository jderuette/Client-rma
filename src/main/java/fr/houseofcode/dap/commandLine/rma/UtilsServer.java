package fr.houseofcode.dap.commandLine.rma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
  * @author rma
 * 5 august. 2019
 * class utils :
 * tools to load correctly the access
 * to google API
 */
public class UtilsServer {

    /**constant to define which browse is using.*/
    private final String uSErAGENT = "Mozilla/5.0";

    /**
     * load the next event on server.
     * @param userKey accept name of person who use the application
     * @return the next event on server using identification
     * @throws IOException exception
     */
    public String getNextEvent(final String userKey) throws IOException {
        String event = callServer("/event/next", userKey);
        return event;
    }

    /**
     * load labels on server.
     * @param userKey accept name of person who use the application
     * @return the labels on server using identification
     * @throws IOException exception
     */
    public String getLabels(final String userKey) throws IOException {
        String label = callServer("/label/print", userKey);
        return label;
    }

    /**
     * load number of unread email on server.
     * @param userKey accept name of person who use the application
     * @return the number of unread email on server using identification
     * @throws IOException exception
     */
    public String getNbUnreadEmail(final String userKey) throws IOException {
        String nbEmail = callServer("/email/nbUnread", userKey);

        return nbEmail;
    }



    /**
     * call the server.
     * @param userKey accept name of person who use the application
     * @param url that the server calls
     * @return a call to the server using identification
     * @throws IOException exception
     */
    private String callServer(final String url, final String userKey)
            throws IOException {

        URL obj = new URL("http://localhost:8080"
                + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", uSErAGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : "
                + "http://localhost:8080" + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }

}
