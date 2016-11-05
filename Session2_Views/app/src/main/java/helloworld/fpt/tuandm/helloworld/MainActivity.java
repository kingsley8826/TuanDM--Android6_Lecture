package helloworld.fpt.tuandm.helloworld;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.toString();

    //view
    private EditText editText;
    private Button btnShowText;
    private Button btnShowImage;
    private TextView textView;
    private ImageView imageView;
    private CheckBox cbGender;
    private SeekBar seekBar;
    private TextView txtSeekBar;
    private SearchView searchView;
    private Button btnTest;
    private ProgressBar progressBar;
    //
    private String content;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        init();
        addListener();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void init() {
        editText = (EditText) findViewById(R.id.edit_text);
        btnShowText = (Button) findViewById(R.id.btn1);
        btnShowImage = (Button) findViewById(R.id.btn2);
        textView = (TextView) findViewById(R.id.txt_content);
        imageView = (ImageView) findViewById(R.id.trollImage);
        cbGender = (CheckBox) findViewById(R.id.cb_gender);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        txtSeekBar = (TextView) findViewById(R.id.txtSeekBar);
        searchView = (SearchView) findViewById(R.id.searchView);
        btnTest = (Button) findViewById(R.id.btnTest);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void addListener() {
        btnShowText.setOnClickListener(this);
        btnShowImage.setOnClickListener(this);
        cbGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "change");
            }
        });
        cbGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click");
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, String.format("onProgressChanged: %s, %s",i, b ));
                txtSeekBar.setText(String.format("%s / %s",seekBar.getProgress(), seekBar.getMax() ));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch");

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG,String.format("onQueryTextSubmit: %s", query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG,String.format("onQueryTextChange: %s", newText));
                return false;
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"tets");
                searchView.clearFocus();
                searchView.setQuery("",false);
                searchView.setIconified(true);
                progressBar.setProgress(progressBar.getProgress()+ 10);
                Log.d(TAG, String.format("progressBar: %s", progressBar.getProgress() ));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: {
                textView.setText(editText.getText().toString());
                break;
            }
            case R.id.btn2: {
                imageView.setImageResource(R.drawable.troll);
                break;
            }
            default:
                break;
        }
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
