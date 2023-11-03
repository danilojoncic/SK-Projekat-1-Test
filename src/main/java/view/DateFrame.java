package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;
import controller.ImportController;
import controller.Ubacivac;
import model.boljeRijesenje.Datumi;
import model.boljeRijesenje.Dogadjaj;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFrame extends JFrame {

    MainFrame mainFrame;
    JButton krajniDatum;
    JButton ucitajDatumeDugme;
    private ArrayList<String> dani = new ArrayList<>();

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
                // Create a SimpleDateFormat object to format the date
                SimpleDateFormat dateFormat = new SimpleDateFormat("d.M");
                Date date = null;
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    int index = vratiIndeksZaDan(dogadjaj.getStavkeDogadjaja().get(4));
                    date = calculateDateForDayOfWeek(Cuvac.getInstance().getRaspored().getDatumOdKadaVazi(),index);

                    System.out.println(date);
                    String datum = dateFormat.format(date);
                    dogadjaj.getStavkeDogadjaja().add(datum);
                }
                Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja().add("Datum");
                Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    System.out.println(dogadjaj);
                }
            }
        });
        this.setVisible(true);
    }
    public int vratiIndeksZaDan(String dan){
        return dani.indexOf(dan);
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

    public static Date calculateDateForDayOfWeek(Date startDate, int dayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        if (dayOfWeek < 0 || dayOfWeek > 6) {
            throw new IllegalArgumentException("Day of the week should be between 0 (Sunday) and 6 (Saturday).");
        }

        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1; // Convert to 0-based indexing

        int diff = dayOfWeek - startDay;

        if (diff <= 0) {
            diff += 7;
        }

        cal.add(Calendar.DAY_OF_YEAR, diff);

        return cal.getTime();
    }


}
