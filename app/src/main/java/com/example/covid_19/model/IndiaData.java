package com.example.covid_19.model;

import org.json.JSONException;
import org.json.JSONObject;

public class IndiaData {
    private final String state;
    private final String confirm;
    private final String cured;
    private final String death;

    public IndiaData(JSONObject jsonObject, String key) throws JSONException {
        this.state = key;
        this.confirm = jsonObject.getString("confirm");
        this.cured = jsonObject.getString("cured");
        this.death = jsonObject.getString("death");
    }


    public String getState() {
        return state;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getCured() { return cured; }

    public String getDeath() {
        return death;
    }

}
