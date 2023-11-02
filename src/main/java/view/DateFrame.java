package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;
import controller.ImportController;
import model.boljeRijesenje.Datumi;
import model.boljeRijesenje.Dogadjaj;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
                Date date = null;
                DefaultTableModel model = (DefaultTableModel)mainFrame.getTabelaRasporeda().getModel();

                for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                    for(String s : dogadjaj.getStavkeDogadjaja()){
                        for(int i = 0; i < dani.length;i++){
                            if(s.equals(dani[i])){
                                int index = Datumi.getInstance().vratiIndeksZaDan(s);
                                date = getDatumZaDanUNedelji(index,Cuvac.getInstance().getRaspored().getDatumOdKadaVazi());



                            }
                        }
                    }
                    dogadjaj.getStavkeDogadjaja().add(date.toString());



                }
                Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                najjace();

                mainFrame.getTabelaRasporeda().revalidate();
                mainFrame.getTabelaRasporeda().repaint();
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
    public Date getDatumZaDanUNedelji(int indexDana, Date pocetniDatum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pocetniDatum);

        if (indexDana < 0 || indexDana > 6) {
            throw new IllegalArgumentException("Index dana u nedelji treba biti u opsegu od 0 do 6.");
        }

        int trenutniDan = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Dan u nedelji gde je 0 ponedeljak, 1 utorak, ...
        int razlika = (indexDana + 7 - trenutniDan) % 7;
        calendar.add(Calendar.DATE, razlika);

        return calendar.getTime();
    }

    public void najjace(){
        java.util.List<String> header = Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja();
        String[] kolone = new String[header.size()];
        kolone = header.toArray(kolone);



        DefaultTableModel model = new DefaultTableModel(kolone, 0);


        int index = 1;
        for (Dogadjaj s : Cuvac.getInstance().getRaspored().getDogadjaji()) {

            List<String> lista = s.getStavkeDogadjaja();
            String[] red = new String[lista.size()];
            red = lista.toArray(red);


            model.addRow(red);
            System.out.println(index++);
        }

        mainFrame.getTabelaRasporeda().setModel(model);
        mainFrame.getTabelaRasporeda().repaint();

        mainFrame.revalidate();
        mainFrame.repaint();
    }


}
