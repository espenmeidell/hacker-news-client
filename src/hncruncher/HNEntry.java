package hncruncher;

import java.awt.*;
import java.net.URI;

/**
 * Created by espen on 22.04.15.
 */
public class HNEntry {

    private String id;
    private String url;
    private String title;
    private String by;
    private int pointCount;
    public HNEntry(String id, String linkTarget, String title, String by, int pointCount){
        this.id = id;
        this.url = linkTarget;
        this.title = title;
        this.by = by;
        this.pointCount = pointCount;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getBy() {
        return by;
    }

    public int getPointCount() {
        return pointCount;
    }


    @Override
    public String toString(){
        return id+" "+title+" by "+by;
    }



}
