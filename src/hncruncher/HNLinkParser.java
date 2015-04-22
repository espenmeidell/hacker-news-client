package hncruncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
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


        return null;
    }




}
