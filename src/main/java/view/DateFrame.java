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

        this.setLayout(new FlowLayout());

        jDateChooser = new JDateChooser();
        potvrdi = new JButton("Potvrdi izbor");



        this.add(jDateChooser);
        this.add(potvrdi);

        potvrdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(mainFrame.isNestoUcitano()){
                    Date date = jDateChooser.getDate();
                    Cuvac.getInstance().getRaspored().setDatumDoKadaVazi(date);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Morate prvo da ucitate raspored");
                }


            }
        });


        this.setVisible(true);

    }

}
