package hncruncher.data;

import hncruncher.HNEntry;

import java.util.Comparator;

/**
 * Comparator to sort by points
 * Created by Espen Meidell on 24.04.15.
 */
public class EntryPointComparator implements Comparator<HNEntry> {

    @Override
    public int compare(HNEntry o1, HNEntry o2) {
        if (o1.getPointCount() > o2.getPointCount()){
            return -1;
        } else if (o1.getPointCount() < o2.getPointCount()){
            return 1;
        }
        return 0;
    }
}
