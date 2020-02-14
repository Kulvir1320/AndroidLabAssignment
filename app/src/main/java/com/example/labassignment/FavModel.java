package com.example.labassignment;

import java.util.ArrayList;

public class FavModel {

    private Double latitude;
    private Double longitude;
    private String address, date;

    private String visited;
    private Integer id;


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

    public String getDate() {
        return date;
    }



    public String getVisited() {
        return visited;
    }

    public Integer getId() {
        return id;
    }

    public FavModel(Double latitude, Double longitude, String address, String date,  String visited, Integer id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.date = date;

        this.visited = visited;
        this.id = id;
    }

    public static ArrayList<FavModel> FavLoc = new ArrayList<>();


}
