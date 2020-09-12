package edu.skku.all_in_hand;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class PaymentActivity extends AppCompatActivity {

    Button payment;
    TextView orderlist;
    EditText request;
    Intent intent;
    int total_price;
    String request_content;
    String orderData;
    int postsuccess=0;

    String REQUEST_URL = "http://54.180.99.61:8080/order/new";
    public static final int LOAD_SUCCESS = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        payment = findViewById(R.id.kakaopay);
        orderlist = findViewById(R.id.orderlist);
        request = findViewById(R.id.request);

        intent = getIntent();
        total_price = intent.getIntExtra("price", 0);
        payment.setText(total_price+"원 카카오페이로 결제하기");
        orderlist.setText("가자미 미역국 - 2개");

        request_content = request.getText().toString();

        orderData = "{\n" +
                "                    \"storeId\":2,\n" +
                "                        \"username\": \"test\",\n" +
                "                        \"request\":\"less salt\",\n" +
                "                        \"orderList\": {\n" +
                "                    \"menu1\": [\"gajami\", 2, 20000],\n" +
                "                },\n" +
                "                    \"totalPrice\": 20000\n" +
                "                }";


        payment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //post
                postJSON();
                intent = new Intent(PaymentActivity.this, PaySuccessActivity.class);
                startActivity(intent);
            }
        });

    }
//
//    private final MyHandler mHandler = new MyHandler(this);
//
//    private static class MyHandler extends Handler {
//        private final WeakReference<PaymentActivity> weakReference;
//
//        public MyHandler(PaymentActivity paymentActivity) {
//            weakReference = new WeakReference<PaymentActivity>(paymentActivity);
//        }
//
//        public void handleMessage(Message msg) {
//            PaymentActivity paymentActivity = weakReference.get();
//            if (paymentActivity != null) {
//                switch (msg.what){
//                    case LOAD_SUCCESS:
//
//                        break;
//                }
//            }
//        }
//
//        @Override
//        public void publish(LogRecord logRecord) {
//
//        }
//
//        @Override
//        public void flush() {
//
//        }
//
//        @Override
//        public void close() throws SecurityException {
//
//        }
//    }

    public void postJSON() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Network logic here
                URL requesturl = null;
                InputStream inputStream = null;
                try {
                    requesturl = new URL(REQUEST_URL);
                    HttpURLConnection  myConnection = (HttpURLConnection) requesturl.openConnection();

                    myConnection.setReadTimeout(10000);
                    myConnection.setConnectTimeout(15000);
                    myConnection.setRequestMethod("POST");
                    myConnection.setDoInput(true);
                    myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    myConnection.connect();


                    OutputStreamWriter writer = new OutputStreamWriter(myConnection.getOutputStream());
                    writer.write(orderData);
                    writer.flush();
                    writer.close();


                    /*
                    StringBuilder sb = new StringBuilder();
                    if (myConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        //Stream을 처리해줘야 하는 귀찮음이 있음.
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(myConnection.getInputStream(), "utf-8"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                        br.close();
                        System.out.println("" + sb.toString());
                        Log.d("Response",sb.toString());
                    } else {
                        System.out.println(myConnection.getResponseMessage());
                        Log.d("Response",myConnection.getResponseMessage());
                    }
                    */
                    int response = myConnection.getResponseCode();
                    Log.d("REST POST", "The response is : " + response);

                } catch (Exception e) {
                    Log.e("REST POST", "Error : " + e.getMessage());
                } finally{
                    if(inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

}
