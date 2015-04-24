package hncruncher.uicomponents;

/**
 * Contains different types of popup windows
 * Created by Espen Meidell on 24.04.15.
 */
public enum PopupType {

    NO_IMAGE(null),
    WARNING("../res/Warning.png"),
    ERROR("../res/Error.png"),
    INFO("../res/Info.png");

    private String imgFile;
    private PopupType(String s){
        this.imgFile = s;
    }


    public String getImgFile(){
        return this.imgFile;
    }
}
