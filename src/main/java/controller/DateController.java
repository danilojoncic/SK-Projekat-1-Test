package controller;

import view.DateFrame;
import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateController {
    MainFrame mainFrame;

    public DateController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();


    }
    public void attachListeners(){

        mainFrame.getPostaviDatum().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.isNestoUcitano())
                    new DateFrame(mainFrame);
                else
                    JOptionPane.showMessageDialog(null,"Ucitajte prvo raspored");
            }
        });
    }


    public MainFrame getMainFrame() {
        return mainFrame;
    }


}
