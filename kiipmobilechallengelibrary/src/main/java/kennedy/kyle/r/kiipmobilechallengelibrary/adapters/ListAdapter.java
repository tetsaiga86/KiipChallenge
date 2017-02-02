package kennedy.kyle.r.kiipmobilechallengelibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import kennedy.kyle.r.kiipmobilechallengelibrary.HackerNewsItem;
import kennedy.kyle.r.kiipmobilechallengelibrary.R;

public class ListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<HackerNewsItem> mDataArrayList;

    public ListAdapter(Context context, ArrayList<HackerNewsItem> items){
        mDataArrayList = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.news_item, parent, false);
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView authorTextView = (TextView) view.findViewById(R.id.authorTextView);
        TextView scoreTextView = (TextView) view.findViewById(R.id.scoreTextView);
        TextView timeTextView = (TextView) view.findViewById(R.id.timeTextView);
        TextView commentsTextView = (TextView) view.findViewById(R.id.commentsTextView);

        HackerNewsItem item = (HackerNewsItem) getItem(position);
        titleTextView.setText(item.getTitle());
        authorTextView.setText(item.getAuthor());
        scoreTextView.setText(item.getScore()+"");
        timeTextView.setText(item.getTime()+"");
        commentsTextView.setText(item.getComments()+"");

        return view;
    }
}
