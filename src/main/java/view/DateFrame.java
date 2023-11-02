package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class DateFrame extends JFrame {

    MainFrame mainFrame;
    JButton krajniDatum;

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

        jDateChooser = new JDateChooser();
        potvrdi = new JButton("Pocetak");



        this.add(jDateChooser);
        this.add(potvrdi);
        this.add(krajniDatum);


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


        this.setVisible(true);

    }

}
