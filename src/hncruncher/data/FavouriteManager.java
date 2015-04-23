package hncruncher.data;

import java.io.*;

/**
 * Manages HN Entries stored as favourites
 * Created by Espen Meidell on 23.04.15.
 */
public class FavouriteManager {

    private static String favFilePath = System.getProperty("user.home")+"/.hnfav";

    private static boolean hasFavouriteFileWithOkPermissions() {
        File file = new File(favFilePath);
        return file.exists() && file.canRead() && file.canWrite();
    }

    private static void createFavouriteFile() throws IOException{
        File file = new File(favFilePath);
        if (!hasFavouriteFileWithOkPermissions()){
            file.createNewFile();
        }
    }

    private static String[] getFavourites() throws IOException {
        if (!hasFavouriteFileWithOkPermissions()) createFavouriteFile();
        BufferedReader br = new BufferedReader(new FileReader(favFilePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null){
            sb.append(line);
            sb.append(" ");
        }
        br.close();
        return sb.toString().split(" ");
    }

    /**
     * Checks if given id is in the favourite list
     * @param id ID to check
     * @return If id is in fav. list
     */
    public static boolean isFavourite(String id){
        try {
            for (String s : getFavourites()) {
                if (s.equals(id)) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Adds an ID to favourite list
     * @param id ID to add
     */
    public static void addToFavourites(String id){
        try {
            if (isFavourite(id)) return;
            PrintWriter pw = new PrintWriter(new FileWriter(favFilePath, true));
            pw.println(id);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an ID from favourites
     * @param id ID to remove
     */
    public static void removeFromFavourites(String id){
        try {
            String[] favs = getFavourites();
            PrintWriter pw = new PrintWriter(new FileWriter(favFilePath));
            for (String f:favs){
                if (!f.equals(id)) pw.println(f);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
