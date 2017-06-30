package org.telegram.telgram;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MindHunter on 2/28/2017.
 */
public class PModel implements Serializable
{
    @SerializedName("p_title")
    private String title;
    @SerializedName("p_content")
    private String content;
    @SerializedName("p_imageUrl")
    private String imageUrl;
    @SerializedName("p_buttonCaption")
    private String buttonCaption;
    @SerializedName("p_link")
    private String link;
    @SerializedName("p_isForce")
    private boolean isForce;
    @SerializedName("p_Type")
    private String type;

    public String getTitle() {
        return title;
    }

    public PModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getButtonCaption() {
        return buttonCaption;
    }

    public PModel setButtonCaption(String buttonCaption) {
        this.buttonCaption = buttonCaption;
        return this;
    }

    public String getLink() {
        return link;
    }

    public PModel setLink(String link) {
        this.link = link;
        return this;
    }

    public boolean isForce() {
        return isForce;
    }

    public PModel setForce(boolean force) {
        isForce = force;
        return this;
    }

    public String getType() {
        return type;
    }

    public PModel setType(String type) {
        this.type = type;
        return this;
    }

    public static enum Types {
        Auto   ("auto",0),
        Notify ("notify", 1),
        Popup  ("popup", 2),
        Join  ("join", 3);

        private String stringValue;
        private int intValue;
        private Types(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }
}
