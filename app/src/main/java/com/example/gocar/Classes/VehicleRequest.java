package com.example.gocar.Classes;

public class VehicleRequest {

    private String vehicle_number;
    private String vehicle_name;
    private int model;
    private int seating_capacity;
    private int cc;
    private String status;
    private String fuel;
    private String username;
    private float rent_per_hour;



    public VehicleRequest() {
    }

    public VehicleRequest(String vehicle_number, String vehicle_name, int model, int seating_capacity, int cc, String status, String fuel, String username, float rent_per_hour) {
        this.vehicle_number = vehicle_number;
        this.vehicle_name = vehicle_name;
        this.model = model;
        this.seating_capacity = seating_capacity;
        this.cc = cc;
        this.status = status;
        this.fuel = fuel;
        this.username = username;
        this.rent_per_hour = rent_per_hour;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setRent_per_hour(float rent_per_hour) {
        this.rent_per_hour = rent_per_hour;
    }

    public String getFuel() {
        return fuel;
    }

    public float getRent_per_hour() {
        return rent_per_hour;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getCc ()
    {
        return cc;
    }

    public void setCc (int cc)
    {
        this.cc = cc;
    }

    public int getSeating_capacity ()
    {
        return seating_capacity;
    }

    public void setSeating_capacity (int seating_capacity)
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

    public int getModel ()
    {
        return model;
    }

    public void setModel (int model)
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
        return "ClassPojo [cc = "+cc+", seating_capacity = "+seating_capacity+", vehicle_number = "+vehicle_number+", model = "+model+", vehicle_name = "+vehicle_name+", status = "+status+"]";
    }
}
