package kennedy.kyle.r.kiipmobilechallengelibrary;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class HackerNewsItem {

    private String mTitle;
    private String mAuthor;
    private String mScore;
    private Date mTime;
    private String mComments;
    private String mUrl;
    private JSONObject mJSONObject;

    public HackerNewsItem(JSONObject json){
        mJSONObject = json;
        try{
            this.mTitle = json.getString("title");
            this.mAuthor = "Author: " + json.getString("by");
            this.mScore = "Score: " + json.getInt("score");
            Date time = new Date(json.getLong("time")*1000);
            this.mTime = time;
            this.mComments = "Comments: " + json.getInt("descendants");
            this.mUrl = json.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSONObject() {
        return mJSONObject;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getScore() {
        return mScore;
    }

    public Date getTime() {
        return mTime;
    }

    public String getComments() {
        return mComments;
    }

    public String getUrl() {
        return mUrl;
    }

}
