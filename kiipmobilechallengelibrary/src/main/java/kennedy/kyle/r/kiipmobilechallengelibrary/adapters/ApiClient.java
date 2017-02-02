package kennedy.kyle.r.kiipmobilechallengelibrary.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
//    private Context mContext;
    private String mUrl;

    public ApiClient(String url){
//        mContext = context;
        mUrl = url;
    }

    public String getUrlJsonString(){
//        if (!ensureNetworkAvailable()) {
//            return null;
//        }
        final String[] bodyString = {null};
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(mUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bodyString[0] = response.body().string();
                Log.i("inside onResponse:", bodyString[0]);

            }
        });
        // FIXME: 2/1/2017
        while(bodyString[0]==null){}
        return bodyString[0];

    }

//    private boolean ensureNetworkAvailable() {
//        if (!isNetworkAvailable(mContext)) {
//            Toast.makeText(, "A Network Problem Occurred",
//                    Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }

//    private boolean isNetworkAvailable(Context context) {
//        ConnectivityManager manager = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//        boolean isAvailable = false;
//        if (networkInfo != null && networkInfo.isConnected()) {
//            isAvailable = true;
//        }
//        return isAvailable;
//    }
}
