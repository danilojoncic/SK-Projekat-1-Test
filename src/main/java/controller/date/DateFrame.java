package controller.date;

import com.toedter.calendar.JDateChooser;
import controller.date.RudarenjeURudnikuController;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateFrame extends JFrame {

    MainFrame mainFrame;
    JButton krajniDatum;
    JButton ucitajDatumeDugme;
    private ArrayList<String> dani = new ArrayList<>();

    JDateChooser jDateChooser;
    RudarenjeURudnikuController rudarenjeURudnikuController;
    JButton potvrdi;

    public DateFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        initialise();
    }

    public void initialise(){
        this.setTitle("Izaberite datum");
        this.setSize(400,280);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());
        krajniDatum = new JButton("Kraj");
        this.setLayout(new FlowLayout());
        ucitajDatumeDugme = new JButton("Ucitaj u model");
        jDateChooser = new JDateChooser();
        potvrdi = new JButton("Pocetak");
        dani.add("PON");
        dani.add("UTO");
        dani.add("SRE");
        dani.add("ČET");
        dani.add("PET");
        dani.add("SUB");
        dani.add("NED");
        this.add(jDateChooser);
        this.add(potvrdi);
        this.add(krajniDatum);
        this.add(ucitajDatumeDugme);
        this.setVisible(true);
        rudarenjeURudnikuController = new RudarenjeURudnikuController(this);
    }



    public Date getDatumZaDanUNedelji(int indexDana, Date pocetniDatum) {
        if (indexDana < 0 || indexDana > 6) {
            throw new IllegalArgumentException("Index dana u nedelji treba biti u opsegu od 0 do 6.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pocetniDatum);

        int trenutniDan = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Day of the week where 0 is Monday, 1 is Tuesday, ...

        while (trenutniDan != indexDana) {
            calendar.add(Calendar.DATE, 1); // Move to the next day
            trenutniDan = (trenutniDan + 1) % 7;
        }

        return calendar.getTime();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JButton getKrajniDatum() {
        return krajniDatum;
    }

    public void setKrajniDatum(JButton krajniDatum) {
        this.krajniDatum = krajniDatum;
    }

    public JButton getUcitajDatumeDugme() {
        return ucitajDatumeDugme;
    }

    public void setUcitajDatumeDugme(JButton ucitajDatumeDugme) {
        this.ucitajDatumeDugme = ucitajDatumeDugme;
    }

    public ArrayList<String> getDani() {
        return dani;
    }

    public void setDani(ArrayList<String> dani) {
        this.dani = dani;
    }

    public JDateChooser getjDateChooser() {
        return jDateChooser;
    }

    public void setjDateChooser(JDateChooser jDateChooser) {
        this.jDateChooser = jDateChooser;
    }

    public JButton getPotvrdi() {
        return potvrdi;
    }

    public void setPotvrdi(JButton potvrdi) {
        this.potvrdi = potvrdi;
    }
}
