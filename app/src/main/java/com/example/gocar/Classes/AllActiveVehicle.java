package com.example.gocar.Classes;

import java.util.ArrayList;

public class AllActiveVehicle {
    private String cc;
    private ArrayList<String> images;

    private String username;

    private String rent_per_hour;

    private String seating_capacity;

    private String vehicle_number;

    private String fuel;

    private String model;

    private String vehicle_name;

    private String status;
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getCc ()
    {
        return cc;
    }

    public void setCc (String cc)
    {
        this.cc = cc;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getRent_per_hour ()
    {
        return rent_per_hour;
    }

    public void setRent_per_hour (String rent_per_hour)
    {
        this.rent_per_hour = rent_per_hour;
    }

    public String getSeating_capacity ()
    {
        return seating_capacity;
    }

    public void setSeating_capacity (String seating_capacity)
    {
        this.seating_capacity = seating_capacity;
    }

    public String getVehicle_number ()
    {
        return vehicle_number;
    }

    public void setVehicle_number (String vehicle_number)
    {
        this.vehicle_number = vehicle_number;
    }

    public String getFuel ()
    {
        return fuel;
    }

    public void setFuel (String fuel)
    {
        this.fuel = fuel;
    }

    public String getModel ()
    {
        return model;
    }

    public void setModel (String model)
    {
        this.model = model;
    }

    public String getVehicle_name ()
    {
        return vehicle_name;
    }

    public void setVehicle_name (String vehicle_name)
    {
        this.vehicle_name = vehicle_name;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cc = "+cc+", owner = "+username+", rent_per_hour = "+rent_per_hour+", seating_capacity = "+seating_capacity+", vehicle_number = "+vehicle_number+", fuel = "+fuel+", model = "+model+", vehicle_name = "+vehicle_name+", status = "+status+"]";
    }
}
