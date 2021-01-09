package com.bezkoder.spring.jwt.mongodb.models;

import java.util.List;

public class ListTest {
    List<Test> full;
    List<Test> reading;
    List<Test> listening;

    public ListTest(List<Test> full, List<Test> reading, List<Test> listening) {
        this.full = full;
        this.reading = reading;
        this.listening = listening;
    }

    public List<Test> getFull() {
        return full;
    }

    public void setFull(List<Test> full) {
        this.full = full;
    }

    public List<Test> getReading() {
        return reading;
    }

    public void setReading(List<Test> reading) {
        this.reading = reading;
    }

    public List<Test> getListening() {
        return listening;
    }

    public void setListening(List<Test> listening) {
        this.listening = listening;
    }
    
    
}
