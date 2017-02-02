package kennedy.kyle.r.kiipmobilechallengelibrary.asynctasks;

import android.os.AsyncTask;
import android.support.v4.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import kennedy.kyle.r.kiipmobilechallengelibrary.HackerNewsItem;
import kennedy.kyle.r.kiipmobilechallengelibrary.adapters.ApiClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetBodyStringTask extends AsyncTask<Pair<URL, GetBodyStringTask.Caller>, Void, String> {
    private ArrayList<HackerNewsItem> mItems = new ArrayList<>();

    @Override
    protected String doInBackground(Pair<URL, GetBodyStringTask.Caller>... params) {
//        ApiClient apiClient = new ApiClient("https://hacker-news.firebaseio.com/v0/topstories.json");
//        String jsonData = apiClient.getUrlJsonString();
//        try {
//            JSONArray storyIdArray = new JSONArray(jsonData);
//            for (int i = 0; i<20; i++){
//                apiClient = new ApiClient("https://hacker-news.firebaseio.com/v0/item/"+storyIdArray.get(i).toString()+".json");
//                mItems.add(new HackerNewsItem(new JSONObject(apiClient.getUrlJsonString())));
////                Log.i("for loop ", "initSDK: "+mApiClient.getUrlJsonString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(params[0].first)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return "";
        }

        return response.body().toString();
    }

    @Override
    protected void onPostExecute(String s) {

    }

    public interface Caller {
        public void setResponseBody(String bodyString);
    }
}
