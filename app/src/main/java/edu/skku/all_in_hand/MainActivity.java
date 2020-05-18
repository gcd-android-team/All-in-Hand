package edu.skku.all_in_hand;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView showJSON;
    Button showJSONbutton;
    String REQUEST_URL ="http://54.180.99.61:8080/stores/test2";
    //
    //https://jsonplaceholder.typicode.com/users
    String TAG = "get json test";
    public static final int LOAD_SUCCESS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showJSON = (TextView) findViewById(R.id.showJSON);
        showJSONbutton = (Button) findViewById(R.id.showJSONbutton);

        showJSONbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJSON();
            }
        });
    }

    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity mainactivity) {
            weakReference = new WeakReference<MainActivity>(mainactivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainactivity = weakReference.get();
            if (mainactivity != null) {
                switch (msg.what){
                case LOAD_SUCCESS:
                    String jsonString = (String)msg.obj;
                    mainactivity.showJSON.setText(jsonString);
                    break;
                }
            }
        }
    }


    public void getJSON() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                String result="";
                try {
                    Log.d(TAG, REQUEST_URL);
                    URL url = new URL(REQUEST_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setReadTimeout(3000);
                    httpURLConnection.setConnectTimeout(3000);
                    //httpURLConnection.setDoOutput(true);
                    //httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();

                    int responseStatusCode = httpURLConnection.getResponseCode();

                    InputStream inputStream;
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();
                    }


                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                    /*
                    JsonReader jsonReader = new JsonReader(inputStreamReader);
                    jsonReader.beginObject(); // Start processing the JSON object
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String value = jsonReader.nextString();
                        result+=value;
                    }
                    */
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    result=sb.toString().trim();

                    Log.d("Result", result);

                    Message message = mHandler.obtainMessage(LOAD_SUCCESS, result);
                    mHandler.sendMessage(message);
                    //result = sb.toString().trim();
                } catch (UnsupportedEncodingException | MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    Log.d("ERR", e.toString());
                    result = e.toString();
                }


            }

        });
        thread.start();
    }

}
