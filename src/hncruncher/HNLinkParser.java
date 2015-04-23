package hncruncher;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Fetches data from hackernews
 * JSON Library: https://code.google.com/p/json-simple/
 *
 * Created by Espen Meidell on 22.04.15.
 */
public class HNLinkParser {


    /**
     * Gets HN ids from given link (usually 500)
     * @param link Where to look for IDs
     * @return Array of IDs
     * @throws IOException In case of IO error
     */
    public static String[] getIDsFromLink(String link) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(link).openStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        int c = 0;
        while ((line = br.readLine()) != null){
            builder.append(line);
        }
        br.close();
        String idtext = builder.toString().replace("[", "");
        idtext = idtext.replace("]", "");
        String[] ids = idtext.split(", ");

        return ids;
    }

    /**
     * Returns the HNEntry associated with the given ID
     * @param id ID to fetch
     * @return HNEntry with data from ID
     * @throws IOException, ParseException In case of IO error or parsing issues
     */
    public static HNEntry getEntryFromID(String id) throws IOException, ParseException{
        String address = "https://hacker-news.firebaseio.com/v0/item/"+id.trim()+".json?print=pretty";
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(address).openStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null){
            builder.append(line);
        }
        br.close();
        JSONObject object = (JSONObject)JSONValue.parse(builder.toString());
        Long score = (Long) object.get("score");

        return new HNEntry(((Long)object.get("id")).toString(), (String)object.get("url"), (String)object.get("title"), (String)object.get("by"), score.intValue());
    }




}
