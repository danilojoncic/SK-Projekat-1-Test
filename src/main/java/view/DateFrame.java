package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;
import model.boljeRijesenje.Datumi;
import model.boljeRijesenje.Dogadjaj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class DateFrame extends JFrame {

    MainFrame mainFrame;
    JButton krajniDatum;
    JButton ucitajDatumeDugme;
    private String[] dani = {"PON","UTO","SRI","CET","PET"};

    JDateChooser jDateChooser;
    JButton potvrdi;

    public DateFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        initialise();
    }

    public void initialise(){
        this.setTitle("Izaberite datum");
        this.setSize(400,280);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());
        krajniDatum = new JButton("Kraj");
        this.setLayout(new FlowLayout());
        ucitajDatumeDugme = new JButton("Ucitaj u model");
        jDateChooser = new JDateChooser();
        potvrdi = new JButton("Pocetak");



        this.add(jDateChooser);
        this.add(potvrdi);
        this.add(krajniDatum);
        this.add(ucitajDatumeDugme);

        krajniDatum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.isNestoUcitano()){
                    Date date = jDateChooser.getDate();
                    Cuvac.getInstance().getRaspored().setDatumDoKadaVazi(date);
                    System.out.println(Cuvac.getInstance().getRaspored().getDatumDoKadaVazi().toString());
                }
                else{
                    JOptionPane.showMessageDialog(null,"Morate prvo da ucitate raspored");
                }
            }
        });

        potvrdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(mainFrame.isNestoUcitano()){
                    Date date = jDateChooser.getDate();
                    Cuvac.getInstance().getRaspored().setDatumOdKadaVazi(date);
                    System.out.println(Cuvac.getInstance().getRaspored().getDatumOdKadaVazi().toString());
                }
                else{
                    JOptionPane.showMessageDialog(null,"Morate prvo da ucitate raspored");
                }


            }
        });

        ucitajDatumeDugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate localDate = null;
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    for(String s : dogadjaj.getStavkeDogadjaja()){
                        for(int i = 0; i < dani.length;i++){
                            if(s.equals(dani[i])){
                                int index = Datumi.getInstance().vratiIndeksZaDan(s);
                                localDate = getDatumZaDanUNedelji(index,Cuvac.getInstance().getRaspored().getPocetni());



                            }
                        }
                    }
                    dogadjaj.getStavkeDogadjaja().add(localDate.toString());
                }
                Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    System.out.println(dogadjaj);
                }
                //nadjem indeks dana na osnovu stringa iz stavke dogadjaja
                //za zadati indeks zajedno sa pocetkom vazenje ja treba da nadjem datum
                //taj datum kastujem u string koji dodajem u stavku dogadjdja kao novo polje
            }
        });


        this.setVisible(true);

    }
    public LocalDate getDatumZaDanUNedelji(int indexDana, LocalDate pocetniDatum) {
        if (indexDana < 0 || indexDana > 6) {
            throw new IllegalArgumentException("Index dana u nedelji treba biti u opsegu od 0 do 6.");
        }

        // Pronalaženje razlike između traženog dana i dana početnog datuma
        int razlika = (indexDana + 7 - pocetniDatum.getDayOfWeek().getValue()) % 7;

        // Dodavanje razlike dana na početni datum kako bi se dobio traženi dan u nedelji
        LocalDate trazeniDatum = pocetniDatum.plusDays(razlika);
        return trazeniDatum;
    }


}
