package hncruncher;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * Class to represent each entry from Hacker News
 * Created by Espen Meidell on 22.04.15.
 */
public class HNEntry {

    private String id;
    private String url;
    private String title;
    private String by;
    private int pointCount;

    /**
     * Creates a new HNEntry
     * @param id Entry ID
     * @param linkTarget Target URL
     * @param title Title of entry
     * @param by Creator of entry
     * @param pointCount Point count of entry
     */
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

    /**
     * Open entry in browser
     */
    public void openInBrowser(){
        System.out.println("Opening: "+getUrl());

        try {
            Main.hostServicesDelegate.showDocument(getUrl());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    /**
     * Open entry in browser
     * @param mouseEvent Event that calls this action
     */
    public void openInBrowser(MouseEvent mouseEvent) {
        openInBrowser();
    }

    @Override
    public String toString(){
        return id+" "+title+" by "+by;
    }



}
