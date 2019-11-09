package com.example.gocar.Classes;

public class Cars {
    private int Rate_per_time;
    private String Brand;
    private String Name;
    private String models;
    private int SitingCapacity;
    private String CarNumber;
    private String Type;
    private String Fuel_type;
    private String location;

    public Cars() {
    }

    public Cars(int rate_per_time, String name, String location) {
        Rate_per_time = rate_per_time;
        Name = name;
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setRate_per_time(int rate_per_time) {
        Rate_per_time = rate_per_time;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public void setSitingCapacity(int sitingCapacity) {
        SitingCapacity = sitingCapacity;
    }

    public void setCarNumber(String carNumber) {
        CarNumber = carNumber;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setFuel_type(String fuel_type) {
        Fuel_type = fuel_type;
    }

    public int getRate_per_time() {
        return Rate_per_time;
    }

    public String getBrand() {
        return Brand;
    }

    public String getName() {
        return Name;
    }

    public String getModels() {
        return models;
    }

    public int getSitingCapacity() {
        return SitingCapacity;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public String getType() {
        return Type;
    }

    public String getFuel_type() {
        return Fuel_type;
    }
}
