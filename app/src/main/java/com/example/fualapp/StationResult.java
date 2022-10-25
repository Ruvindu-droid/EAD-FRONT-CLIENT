package com.example.fualapp;

public class StationResult {

    private String stationname;

    private String petrolarrivaltime;

    private String petrolfinishtime;

    private String dieselarrivaltime;

    private String dieselfinishtime;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    private String status;

    // Newly added ----
    private String _id;

    private String createdAt;

    private String updatedAt;

    private String __v;

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getPetrolarrivaltime() {
        return petrolarrivaltime;
    }

    public void setPetrolarrivaltime(String petrolarrivaltime) {
        this.petrolarrivaltime = petrolarrivaltime;
    }

    public String getPetrolfinishtime() {
        return petrolfinishtime;
    }

    public void setPetrolfinishtime(String petrolfinishtime) {
        this.petrolfinishtime = petrolfinishtime;
    }

    public String getDieselarrivaltime() {
        return dieselarrivaltime;
    }

    public void setDieselarrivaltime(String dieselarrivaltime) {
        this.dieselarrivaltime = dieselarrivaltime;
    }

    public String getDieselfinishtime() {
        return dieselfinishtime;
    }

    public void setDieselfinishtime(String dieselfinishtime) {
        this.dieselfinishtime = dieselfinishtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Number getQueue() {
        return queue;
    }

    public void setQueue(Number queue) {
        this.queue = queue;
    }

    private Number queue;

}
