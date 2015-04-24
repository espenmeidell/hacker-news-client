package hncruncher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Support class for Main, stores entries while loading from web
 * Created by Espen Meidell on 24.04.15.
 */
public class DataControl {
    private  Main main;
    private  List<HNEntry> entryList = new ArrayList<>();

    /**
     * Sets the main instance
     * @param m
     */
    public  void setMain(Main m){
        main = m;
    }

    /**
     * Adds an entry to the list
     * @param e Entry to add
     */
    public  void addEntry(HNEntry e){
        entryList.add(e);
    }

    /**
     * Get all entries
     * @return Entries
     */
    public  List<HNEntry> getEntries(){
        return entryList;
    }

    /**
     * Removes existing entries from the list
     */
    public  void clearEntries(){
        entryList.removeAll(entryList);
    }

    /**
     * Sort entries by given comparator
     * @param comp Comparator
     */
    public void sortEntries(Comparator<HNEntry> comp) {
        entryList.sort(comp);
    }

}
