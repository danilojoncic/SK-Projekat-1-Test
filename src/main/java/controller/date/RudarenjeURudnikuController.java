package controller.date;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class RudarenjeURudnikuController {
    DateFrame dateFrame;
    Map<Date,Integer> hm = new LinkedHashMap<>();

    public RudarenjeURudnikuController(DateFrame dateFrame) {
        this.dateFrame = dateFrame;
        hm = createDateIndexMap(2023);
        attachListeners();
    }

    private void attachListeners(){
        dateFrame.getKrajniDatum().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateFrame.getMainFrame().isNestoUcitano()){
                    Date date = dateFrame.getjDateChooser().getDate();
                    System.out.println("Krajni datum -> " + date.toString());
                    Cuvac.getInstance().getRaspored().setDatumDoKadaVazi(date);
                    System.out.println("Stampanje iz Cuvaca iz krajnjeg -> " + Cuvac.getInstance().getRaspored().getDatumDoKadaVazi().toString());

                }
                else{
                    JOptionPane.showMessageDialog(null,"Morate prvo da ucitate raspored");
                }
            }
        });
        dateFrame.getPocetniDatum().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(dateFrame.getMainFrame().isNestoUcitano()){
                    Date date = dateFrame.getjDateChooser().getDate();
                    System.out.println("Pocetni -> " + date.toString());
                    Cuvac.getInstance().getRaspored().setDatumOdKadaVazi(date);
                    System.out.println("Stampanje iz Cuvaca -> " + Cuvac.getInstance().getRaspored().getDatumOdKadaVazi().toString());
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
                SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.y");
                Date date = null;
                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    //ovo pravi problem kada se obrise neka kolona i pokusa ponovo
                    int index = vratiIndeksZaDan(dogadjaj.getStavkeDogadjaja().get(4));
                    date = calculateDateForDayOfWeek(Cuvac.getInstance().getRaspored().getDatumOdKadaVazi(),index);
                    Date od = Cuvac.getInstance().getRaspored().getDatumOdKadaVazi();
                    Date doV = Cuvac.getInstance().getRaspored().getDatumDoKadaVazi();
                    Date dan = new Date();
                    int x = 0;
                    int y = 0;
                    int i = 0;
                    for(Date date1 : hm.keySet()){
                        if(date1.equals(od)){
                            x = i;
                        }
                        if(date1.equals(doV)){
                            y = i;
                        }
                        i++;
                    }
                    List<Date> datumi = hm.keySet().stream().toList();
                    for(int j = x; j <= y;j++){
                        if(hm.get(datumi.get(j)) == index){
                            dan = datumi.get(j);
                        }
                    }
                    System.out.println("date stampa " + date);
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
            throw new IllegalArgumentException("Day of the week should be between 0 (Monday) and 6 (Sunday).");
        }

        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 2; // Convert to 0-based indexing (Monday is 0)

        int diff = dayOfWeek - startDay;
        if (diff < 0) {
            diff += 7;
        }

        cal.add(Calendar.DAY_OF_YEAR, diff);

        return cal.getTime();
    }


    public int vratiIndeksZaDan(String dan){
        return dateFrame.getDani().indexOf(dan);
    }


    public static Map<Date, Integer> createDateIndexMap(int year){
        // Initialize an empty hash map
        Map<Date, Integer> dateIndexMap = new LinkedHashMap<>();

        // Define the starting date for the given year
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Iterate through each day of the year
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 365; i++) {
            // Get the current date
            Date currentDate = calendar.getTime();

            // Get the day of the week index (Monday = 0, Sunday = 6)
            int dayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;

            // Adjust Sunday to index 6
            if (dayOfWeekIndex == -1) {
                dayOfWeekIndex = 6;
            }

            // Add the entry to the hash map
            dateIndexMap.put(currentDate, dayOfWeekIndex);

            // Move to the next day
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return dateIndexMap;
    }
}
