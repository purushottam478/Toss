package com.example.rajulnahar.toss.Loaders;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajulnahar.toss.Objects.DebateClaim;
import com.example.rajulnahar.toss.Objects.DebateFact;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajul Nahar on 13-02-2017.
 */

public class DebateClaimLoader {
    private String url = "http://toss-service-test.us-east-1.elasticbeanstalk.com/claims/getFeaturedClaimsById/";


    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    private boolean isTaskComplete;
    private DebateClaim debateClaim;

    public DebateClaimLoader(Context context){
        requestQueue = Volley.newRequestQueue(context);
        debateClaim = new DebateClaim();
    }

    public void loadDebateClaim(String claimGuid){
        debateClaim.setDebateClaimsGuid(claimGuid);
        isTaskComplete = false;
        stringRequest = new StringRequest(url +claimGuid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                handleResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isTaskComplete = true;
            }
        });
        requestQueue.add(stringRequest);

    }

    private void handleResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("featuredClaimsList");
            for (int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    if(jsonObject1.getString("claimType").equals( "HEADS")){
                        debateClaim.setHeadsText(jsonObject1.getString("claimBody"));
                       // debateClaim.setHeadsViewCount(jsonObject1.getInt("viewCount"));
                        debateClaim.setHeadsAuthorId(jsonObject1.getInt("authorId"));
                    }else{
                        debateClaim.setTailsText(jsonObject1.getString("claimBody"));
                       // debateClaim.setTailsViewCount(jsonObject1.getInt("viewCount"));
                        debateClaim.setTailsAuthorId(jsonObject1.getInt("authorId"));
                    }
            }
            Log.e("DebateClaimLoader",debateClaim.getHeadsText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        isTaskComplete = true;

    }

    public boolean isTaskComplete() {
        return isTaskComplete;
    }

    public DebateClaim getDebateClaim() {
        return debateClaim;
    }
}
