package controller;

import model.boljeRijesenje.Raspored;

import java.util.List;

public class Cuvac {


    Raspored raspored;
    List<String> header;
    private static Cuvac instance = null;

    private Cuvac() {
    }


    public static Cuvac getInstance(){
        if(instance == null){
            instance = new Cuvac();
        }
        return instance;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public Raspored getRaspored() {
        return raspored;
    }

    public void setRaspored(Raspored raspored) {
        this.raspored = raspored;
    }
}
