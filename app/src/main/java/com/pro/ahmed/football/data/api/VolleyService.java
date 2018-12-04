package com.pro.ahmed.football.data.api;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pro.ahmed.football.data.patterns.VolleySingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyService {
    IResult mResultCallback = null;
    Application mContext;
    private Map<String, String> header;


    public VolleyService(IResult resultCallback, Application context) {
        mResultCallback = resultCallback;
        mContext = context;
        header = new HashMap<>();
    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public void getDataVolley(final String requestType, String url) {
        try {

            JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                            Log.v("Response Service", response.toString());
                            if (mResultCallback != null)
                                mResultCallback.notifySuccess(requestType, response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (mResultCallback != null)
                                mResultCallback.notifyError(requestType, error);
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return header;
                }
            };

            VolleySingleton.getInstance(mContext).addToRequestQueue(jsObjRequest);
        } catch (Exception e) {

        }
    }

}
