package kennedy.kyle.r.kiipmobilechallenge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import kennedy.kyle.r.kiipmobilechallengelibrary.HackerNews;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final HackerNews hn = new HackerNews();
        hn.initSDK(MainActivity.this);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                hn.showHackerNews();
            }
        });
    }

}
