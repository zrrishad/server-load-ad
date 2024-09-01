package com.example.phpadcontrol2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    LinearLayout adContainerView;
    ProgressBar edProg;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adContainerView=findViewById(R.id.adContainerView);
        edProg=findViewById(R.id.edProg);
        tvText=findViewById(R.id.tvText);


        // Create a new ad view.
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");
        adView.setAdSize(AdSize.BANNER);

// Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);








        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.32.112/apps/php.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        edProg.setVisibility(View.GONE);
                        // Display the first 500 characters of the response string.

                        if (response.contains("ShowAd")){
                            adContainerView .setVisibility(View.VISIBLE);

                            AdRequest adRequest = new AdRequest.Builder().build();
                            adView.loadAd(adRequest);




                        }else {
                            adContainerView.setVisibility(View.GONE);


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvText.setText("That didn't work!"+error.getMessage());
            }
        });

// Add the request to the RequestQueue.


        queue.add(stringRequest);





    }
}