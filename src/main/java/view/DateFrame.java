package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;
import controller.Ubacivac;
import controller.date.PostavljanjeDatumaController;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateFrame extends JFrame {
    public JLabel label;
    MainFrame mainFrame;
    JComboBox jCheckBox;
    JButton krajniDatum;
    JButton ucitajDatumeDugme;
    private ArrayList<String> dani = new ArrayList<>();

    JDateChooser jDateChooser;
    PostavljanjeDatumaController postavljanjeDatumaController;
    JButton pocetniDatum;

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
        pocetniDatum = new JButton("Pocetak");
        jCheckBox = new JComboBox<>();
        label = new JLabel("Izaberi kolonu koja se odnosi na dane: ");
        dani.add("PON"); //0
        dani.add("UTO");
        dani.add("SRE");
        dani.add("ČET");
        dani.add("PET");
        dani.add("SUB");
        dani.add("NED"); //6
        this.add(label);
        this.add(jCheckBox);
        zoviRedzica(jCheckBox,0);
        this.add(jDateChooser);
        this.add(pocetniDatum);
        this.add(krajniDatum);
        this.add(ucitajDatumeDugme);
        this.setVisible(true);
        postavljanjeDatumaController = new PostavljanjeDatumaController(this);
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

    public JButton getPocetniDatum() {
        return pocetniDatum;
    }

    public void setPocetniDatum(JButton pocetniDatum) {
        this.pocetniDatum = pocetniDatum;
    }

    public void zoviRedzica(JComboBox jComboBox,int index){
        //jComboBox.addItem(" ");
        for(String s : Cuvac.getInstance().getHeader()){
            if(Ubacivac.getInstance().postojiUComboBox(jComboBox,s)){
                continue;
            }
            jComboBox.addItem(s);
            //jComboBox.setSelectedItem(" ");
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JComboBox getjCheckBox() {
        return jCheckBox;
    }

    public void setjCheckBox(JComboBox jCheckBox) {
        this.jCheckBox = jCheckBox;
    }
}
