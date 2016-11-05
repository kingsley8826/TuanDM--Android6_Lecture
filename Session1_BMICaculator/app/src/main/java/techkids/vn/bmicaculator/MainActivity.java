package techkids.vn.bmicaculator;

import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private EditText edtWeight;
    private EditText edtHeight;
    private TextView txtResult;
    private Button btnResult;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        addListeners();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addListeners() {
        btnResult.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //logcat
                Log.d(TAG, "onClick");
                float weight;
                float height;
                float bmiIndex = 0;
                String result;
                try {
                    weight = Float.parseFloat(edtWeight.getText().toString());
                    height = Float.parseFloat(edtHeight.getText().toString()) / 100;
                    bmiIndex = weight / (height * height);
//                    DecimalFormat df = new DecimalFormat("0.#");
//                    result = "Your BMI is " + String.valueOf(df.format(bmiIndex)) + ", ";
                    result = "Your BMI is " +  bmiIndex + ", ";
                }catch (Exception ex){
                    result = "Your input is wrong, ";
                }
                String state;
                if (bmiIndex > 0 && bmiIndex < 16) {
                    state = "Severely underweight";
                } else if (bmiIndex >= 16 && bmiIndex < 18.5) {
                    state = "Underweight";
                } else if (bmiIndex >= 18.5 && bmiIndex < 25) {
                    state = "Normal";
                } else if (bmiIndex >= 25 && bmiIndex < 30) {
                    state = "Overweight";
                } else if (bmiIndex >= 30){
                    state = "Obese";
                }else{
                    state = "Input again !";
                }
                result += state;
                txtResult.setText(result);
            }
        });
    }

    private void getReferences() {
        edtWeight = (EditText) findViewById(R.id.edt_weight);
        edtHeight = (EditText) findViewById(R.id.edt_height);
        btnResult = (Button) findViewById(R.id.btn_calculator);
        txtResult = (TextView) findViewById(R.id.txt_result);
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
