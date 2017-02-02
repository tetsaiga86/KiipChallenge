package kennedy.kyle.r.kiipmobilechallengelibrary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kennedy.kyle.r.kiipmobilechallengelibrary.Dialogs.HackerNewsDialog;
import kennedy.kyle.r.kiipmobilechallengelibrary.adapters.ApiClient;

public class HackerNews {


    private ArrayList<HackerNewsItem> mItems = new ArrayList<>();
    private Activity mActivity;
    private ApiClient mApiClient;
    private String mJsonData;
    private JSONArray mStoryIdArray;

    public void initSDK(Context context){
        mActivity = (Activity)context;
        mApiClient = new ApiClient(mActivity,"https://hacker-news.firebaseio.com/v0/topstories.json");
//        Log.i("inside initSDK:", mApiClient.getUrlJsonString()+"");
        mJsonData = mApiClient.getUrlJsonString();
        try {
            mStoryIdArray = new JSONArray(mJsonData);
            for (int i = 0; i<20; i++){
                mApiClient = new ApiClient(mActivity, "https://hacker-news.firebaseio.com/v0/item/"+mStoryIdArray.get(i).toString()+".json");
                mItems.add(new HackerNewsItem(new JSONObject(mApiClient.getUrlJsonString())));
//                Log.i("for loop ", "initSDK: "+mApiClient.getUrlJsonString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showHackerNews(){
        HackerNewsDialog dialog = new HackerNewsDialog();
        dialog.setHackerNewsItems(mItems);
        dialog.show(mActivity.getFragmentManager(), "hacker news");
    }

}
