package kennedy.kyle.r.kiipmobilechallengelibrary;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import kennedy.kyle.r.kiipmobilechallengelibrary.Dialogs.HackerNewsDialog;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HackerNews {
    public static String HACKER_NEWS_INDEX_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    public static String HACKER_NEWS_ITEM_URL = "https://hacker-news.firebaseio.com/v0/item/:id.json";
    private Activity mActivity;
    private String mJsonData;


    public void initSDK(Context context){
        mActivity = (Activity)context;

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(params[0])
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    return "";
                }

                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String s) {
                HackerNews.this.mJsonData = s;
                Log.i("hackernews", "onPostExecute: "+HackerNews.this.mJsonData);
            }
        }.execute(HACKER_NEWS_INDEX_URL);

    }

    public void showHackerNews(){
        Integer[] topIds = new Integer[20];

        try {
            JSONArray ids = new JSONArray(mJsonData);
            for(int i = 0; i < 20; i++) {
                topIds[i] = ids.getInt(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new AsyncTask<Integer, Void, ArrayList<HackerNewsItem>>() {

            @Override
            protected ArrayList<HackerNewsItem> doInBackground(Integer... params) {
                OkHttpClient client = new OkHttpClient();
                ArrayList<HackerNewsItem> responses = new ArrayList<>(params.length);

                for (int id : params) {
                    Request request = new Request.Builder()
                            .url(HACKER_NEWS_ITEM_URL.replace(":id", String.valueOf(id)))
                            .build();

                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String responseString = response.body().string();

                        responses.add(new HackerNewsItem(new JSONObject(responseString)));
                    } catch (IOException e) {
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return responses;
            }

            @Override
            protected void onPostExecute(ArrayList<HackerNewsItem> s) {
                HackerNewsDialog dialog = new HackerNewsDialog();
                dialog.setHackerNewsItems(s);
                dialog.show(mActivity.getFragmentManager(), "hacker news");
            }
        }.execute(topIds);


    }

}
