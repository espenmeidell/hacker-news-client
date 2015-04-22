package hncruncher;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Fetches data from hackernews
 * JSON Library: https://code.google.com/p/json-simple/
 *
 * Created by espen on 22.04.15.
 */
public class HNLinkParser {

    public static ArrayList<HNEntry> getLinksFromHN(String link) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(link).openStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null){
            builder.append(line);
        }
        br.close();
        String idtext = builder.toString().replace("[", "");
        idtext = idtext.replace("]", "");
        String[] ids = idtext.split(", ");
        try {
            for (String id : ids) {
                System.out.println(getEntryFromID(id));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    private static HNEntry getEntryFromID(String id) throws Exception{
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
