package com.pro.ahmed.football.data.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

// get result
public interface IResult {

    void notifySuccess(String requestType, JSONObject response);

    void notifyError(String requestType, VolleyError error);
}
