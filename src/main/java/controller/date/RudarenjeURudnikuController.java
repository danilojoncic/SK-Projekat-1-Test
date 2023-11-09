package controller.date;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RudarenjeURudnikuController {
    DateFrame dateFrame;

    public RudarenjeURudnikuController(DateFrame dateFrame) {
        this.dateFrame = dateFrame;
        attachListeners();
    }

    private void attachListeners(){
        dateFrame.getKrajniDatum().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateFrame.getMainFrame().isNestoUcitano()){
                    Date date = dateFrame.getjDateChooser().getDate();
                    Cuvac.getInstance().getRaspored().setDatumDoKadaVazi(date);
                    System.out.println(Cuvac.getInstance().getRaspored().getDatumDoKadaVazi().toString());

                }
                else{
                    JOptionPane.showMessageDialog(null,"Morate prvo da ucitate raspored");
                }
            }
        });
        dateFrame.getPotvrdi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(dateFrame.getMainFrame().isNestoUcitano()){
                    Date date = dateFrame.getjDateChooser().getDate();
                    Cuvac.getInstance().getRaspored().setDatumOdKadaVazi(date);
                    System.out.println(Cuvac.getInstance().getRaspored().getDatumOdKadaVazi().toString());
                }
                else{
                    JOptionPane.showMessageDialog(null,"Morate prvo da ucitate raspored");
                }
            }
        });

        dateFrame.getUcitajDatumeDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a SimpleDateFormat object to format the date
                SimpleDateFormat dateFormat = new SimpleDateFormat("d.M");
                Date date = null;
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    //ovo pravi problem kada se obrise neka kolona i pokusa ponovo
                    int index = vratiIndeksZaDan(dogadjaj.getStavkeDogadjaja().get(4));
                    date = calculateDateForDayOfWeek(Cuvac.getInstance().getRaspored().getDatumOdKadaVazi(),index);

                    System.out.println(date);
                    String datum = dateFormat.format(date);
                    dogadjaj.getStavkeDogadjaja().add(datum);
                }
                Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja().add("Datum");
                Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                Ubacivac.getInstance().ubaciBackendUTabelu(dateFrame.getMainFrame(),Cuvac.getInstance().getRaspored());
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    System.out.println(dogadjaj);
                }
            }
        });


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

    public int vratiIndeksZaDan(String dan){
        return dateFrame.getDani().indexOf(dan);
    }
}
