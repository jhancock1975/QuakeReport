package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by jhancock2010 on 1/27/18.
 * POJO for list item data
 */

public class QuakeListItem {

    public QuakeListItem(double magnitude, String location, Date date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }

    private double magnitude;
    private String location;
    private Date date;

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
