package controller;

import model.Specifikacija;
import model.boljeRijesenje.Raspored;

import java.util.List;

public class Cuvac {

    int kolonaDana;
    Raspored raspored;
    List<String> header;
    private Specifikacija implementacija;

    int originalBrojDogadjaja;
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


    public Specifikacija getImplementacija() {
        return implementacija;
    }

    public void setImplementacija(Specifikacija implementacija) {
        this.implementacija = implementacija;
    }

    public static void setInstance(Cuvac instance) {
        Cuvac.instance = instance;
    }

    public void setRaspored(Raspored raspored) {
        this.raspored = raspored;
    }

    public int getKolonaDana() {
        return kolonaDana;
    }

    public void setKolonaDana(int kolonaDana) {
        this.kolonaDana = kolonaDana;
    }

    public int getOriginalBrojDogadjaja() {
        return originalBrojDogadjaja;
    }

    public void setOriginalBrojDogadjaja(int originalBrojDogadjaja) {
        this.originalBrojDogadjaja = originalBrojDogadjaja;
    }
}
