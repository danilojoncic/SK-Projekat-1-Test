package controller.date;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;
import view.DateFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class PostavljanjeDatumaController {
    DateFrame dateFrame;
    Map<Date,Integer> hm = new LinkedHashMap<>();

    public PostavljanjeDatumaController(DateFrame dateFrame) {
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
                if(dateFrame.getMainFrame().getImp1RB().isSelected()){
                    Cuvac.getInstance().setKolonaDana(dateFrame.getjCheckBox().getSelectedIndex());
                    dateFrame.label.setText("Kolona dana je uzeta!");
                    Date od = Cuvac.getInstance().getRaspored().getDatumOdKadaVazi();
                    Date doV = Cuvac.getInstance().getRaspored().getDatumDoKadaVazi();
                    // Create a SimpleDateFormat object to format the date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.y");
                    Date date = null;
                    for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                        //ovo pravi problem kada se obrise neka kolona i pokusa ponovo
                        int index = vratiIndeksZaDan(dogadjaj.getStavkeDogadjaja().get(Cuvac.getInstance().getKolonaDana()));
                        date = calculateDateForDayOfWeek(Cuvac.getInstance().getRaspored().getDatumOdKadaVazi(),index);

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
                    osveziComboBox();
                    for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                        System.out.println(dogadjaj);
                    }
                    int brojMaxNedelja = razlikaUDanima(od,doV) / 7;

                    for (int i = 1; i <= brojMaxNedelja; i++) {
                        String unos = "Nedelja " + i;
                        dateFrame.getMainFrame().getComboBoxZaNedelje().addItem(unos);
                    }
                    dateFrame.getMainFrame().getComboBoxZaNedelje().setVisible(true);
                    List<Dogadjaj> tempDogadjaji = new ArrayList<>();
                    System.out.println("PRVI"+Cuvac.getInstance().getRaspored().getDogadjaji().get(Cuvac.getInstance().getRaspored().getDogadjaji().size()-1));
                    System.out.println("DRUGI" +Cuvac.getInstance().getRaspored().getDogadjaji().get(Cuvac.getInstance().getRaspored().getDogadjaji().size()-2));
                    for(int i = 1; i <= brojMaxNedelja; i++){
                        for (Dogadjaj d : Cuvac.getInstance().getRaspored().getDogadjaji()) {
                            String string = d.getStavkeDogadjaja().get(d.getStavkeDogadjaja().size() - 1);
                            System.out.println(string);
                            if(!string.contains("."))
                                continue;
                            String[] niz = string.split("\\.");
                            int dan = Integer.parseInt(niz[0]);
                            int mesec = Integer.parseInt(niz[1]);
                            int godina = Integer.parseInt(niz[2])%1000 + 100;

                            System.out.println("DAN: " + dan + " Mesec: " + mesec + " Godina: " + godina);

                            Date date1 = new Date(godina,mesec-1 ,dan);
                            date1 = dodajDane(date1,i*7);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
                            String lol = simpleDateFormat.format(date1);
                            List<String> temp = new ArrayList<>(d.getStavkeDogadjaja());
                            temp.remove(temp.size()-1);
                            temp.add(lol);
                            Dogadjaj dogadjaj = new Dogadjaj(temp);
                            tempDogadjaji.add(dogadjaj);

                        }
                    }
                    for (Dogadjaj dogadjaj : tempDogadjaji) {
                        Cuvac.getInstance().getRaspored().getDogadjaji().add(dogadjaj);
                    }
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(dateFrame.getMainFrame(),Cuvac.getInstance().getRaspored());

                } else if (dateFrame.getMainFrame().getImp2RB().isSelected()) {
                    Cuvac.getInstance().setKolonaDana(dateFrame.getjCheckBox().getSelectedIndex());
                    dateFrame.label.setText("Kolona dana je uzeta!");
                    Date od = Cuvac.getInstance().getRaspored().getDatumOdKadaVazi();
                    Date doV = Cuvac.getInstance().getRaspored().getDatumDoKadaVazi();
                    // Create a SimpleDateFormat object to format the date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.y");
                    String datumOd = dateFormat.format(od);
                    String datumDo = dateFormat.format(doV);

                    Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja().add("Vazi Od");
                    Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja().add("Vazi Do");

                    for (Dogadjaj d : Cuvac.getInstance().getRaspored().getDogadjaji()) {
                        d.getStavkeDogadjaja().add(datumOd);
                        d.getStavkeDogadjaja().add(datumDo);

                    }
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(dateFrame.getMainFrame(),Cuvac.getInstance().getRaspored());
                    dateFrame.getMainFrame().revalidate();
                    dateFrame.getMainFrame().repaint();
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
    public void osveziComboBox(){
        JComboBox comboBox = dateFrame.getMainFrame().getComboBox();

        comboBox.removeAllItems();
        for (String date : Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja()) {
            comboBox.addItem(date);
        }
    }
    private static int razlikaUDanima(Date prviDatum, Date drugiDatum) {

        long razlika = drugiDatum.getTime() - prviDatum.getTime();


        return Math.toIntExact(Math.round((double) razlika / (1000 * 60 * 60 * 24)));
    }
    private static Date dodajDane(Date datum, int brojDana) {
        Calendar kalendar = Calendar.getInstance();
        kalendar.setTime(datum);
        kalendar.add(Calendar.DAY_OF_MONTH, brojDana);
        return kalendar.getTime();
    }
    private static int mesecUInt(String s){
        if(s.equalsIgnoreCase("jan"))
            return 0;
        if(s.equalsIgnoreCase("feb"))
            return 1;
        if(s.equalsIgnoreCase("mar"))
            return 2;
        if(s.equalsIgnoreCase("apr"))
            return 3;
        if(s.equalsIgnoreCase("may"))
            return 4;
        if(s.equalsIgnoreCase("jun"))
            return 5;
        if(s.equalsIgnoreCase("jul"))
            return 6;
        if(s.equalsIgnoreCase("aug"))
            return 7;
        if(s.equalsIgnoreCase("sep"))
            return 8;
        if(s.equalsIgnoreCase("oct"))
            return 9;
        if(s.equalsIgnoreCase("nov"))
            return 10;
        if(s.equalsIgnoreCase("dec"))
            return 11;
        return 0;
    }

}
