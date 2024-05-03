package org.sc;

import org.sc.data.Flat;
import org.sc.data.House;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 5760575944040770153L;
    private String message = null;
    private Flat flat = null;
    private House house = null;

    public Request(String message, Flat flat) {
        this.message = message;
        this.flat = flat;
    }

    public Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}