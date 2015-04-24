package hncruncher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by espen on 24.04.15.
 */
public class DataControl {
    private  Main main;
    private  List<HNEntry> entryList = new ArrayList<>();

    public  void setMain(Main m){
        main = m;
    }

    public  void addEntry(HNEntry e){
        entryList.add(e);
    }

    public  List<HNEntry> getEntries(){
        return entryList;
    }


}
