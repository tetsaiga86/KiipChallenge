package kennedy.kyle.r.kiipmobilechallengelibrary.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kennedy.kyle.r.kiipmobilechallengelibrary.HackerNewsItem;
import kennedy.kyle.r.kiipmobilechallengelibrary.R;

public class HackerNewsDialog extends DialogFragment{
    private ListAdapter mListAdapter;
    private ArrayList<HackerNewsItem> mItems;
    private ListView mListView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.show_hacker_news_view, null);
        mListView = (ListView) v.findViewById(R.id.newsListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = mItems.get(position).getUrl();
                if (url == null){
                    Toast.makeText(getActivity(), "This story does not have a URL", Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }

                Log.i("Dialog", "onItemClick: "+url);
            }
        });
        mListAdapter = new kennedy.kyle.r.kiipmobilechallengelibrary.adapters.ListAdapter(getActivity(), mItems);
        mListView.setAdapter(mListAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        return builder.create();
    }

    public void setHackerNewsItems(ArrayList<HackerNewsItem> items){
        mItems = items;
    }

}
