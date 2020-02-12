package com.example.labassignment;

import java.util.ArrayList;

public class FavModel {

    private Double latitude;
    private Double longitude;
    private String address;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }




    public FavModel(Double latitude, Double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public static ArrayList<FavModel> FavLoc = new ArrayList<>();


}
