package com.example.gocar.Classes;

public class BookingDTO {
    private Integer bookingId;
    private String vehicleNumber;
    private String sellerId;
    private String customerId;
    private String start;
    private String end;
    private Float rentPerHour;
    private String bookingTime;

    public BookingDTO(Integer bookingId, String vehicleNumber, String sellerId, String customerId, String start, String end, Float rentPerHour, String bookingTime) {
        this.bookingId = bookingId;
        this.vehicleNumber = vehicleNumber;
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.start = start;
        this.end = end;
        this.rentPerHour = rentPerHour;
        this.bookingTime = bookingTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getSellerId() {
        return sellerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public Float getRentPerHour() {
        return rentPerHour;
    }
    public void setRentPerHour(Float rentPerHour) {
        this.rentPerHour = rentPerHour;
    }
}

