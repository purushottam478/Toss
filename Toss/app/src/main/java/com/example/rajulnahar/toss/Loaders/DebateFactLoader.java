package com.example.rajulnahar.toss.Loaders;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class DebateFactLoader {

    private String url = "http://toss-service-test.us-east-1.elasticbeanstalk.com/facts/getFeaturedFactsQuery?";
    private String url_exclusiveStartKey = "exclusiveStartKey=";
    private String url_limit = "&limit=";
    private String url_isScanForward = "&isScanForward=";
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    private boolean isTaskComplete;
    private List<DebateFact> debateFactList;

    public DebateFactLoader(Context context){
        requestQueue = Volley.newRequestQueue(context);
        debateFactList= new ArrayList<>();
    }

    public void loadDebateFact(int exclusiveStartKey, int limit, boolean isScanForward){
        isTaskComplete = false;    
        stringRequest = new StringRequest(url +
                    url_exclusiveStartKey + String.valueOf(exclusiveStartKey) +
                    url_limit + String.valueOf(limit) +
                    url_isScanForward + String.valueOf(isScanForward), new Response.Listener<String>() {
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
        debateFactList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("debateFactsList");
            for (int i = 0;i<jsonArray.length();i++){
                debateFactList.add(new Gson().fromJson(jsonArray.getString(i),DebateFact.class));
                Log.e("debateFactLoader",jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        isTaskComplete = true;

    }

    public boolean isTaskComplete() {
        return isTaskComplete;
    }

    public List<DebateFact> getDebateFactList() {
        return debateFactList;
    }
}
