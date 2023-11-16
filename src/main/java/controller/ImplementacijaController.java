package controller;

import view.DateFrame;
import model.Specifikacija;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImplementacijaController {
    MainFrame mainFrame;
    Specifikacija specifikacija;

    public ImplementacijaController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }


    private void attachListeners(){
        mainFrame.getFirmetinaBajo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.getImp2RB().isSelected()){
                    Cuvac.getInstance().getRaspored().nejmarUPetercu(1);
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                    new DateFrame(mainFrame);
                }else if(mainFrame.getImp1RB().isSelected()){
                    Cuvac.getInstance().getRaspored().nejmarUPetercu(0);
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                    new DateFrame(mainFrame);
                }else{
                    JOptionPane.showMessageDialog(null,"IGRAM IGRAM VRTIM SE KO CIGRA< BAJO SELEKTUJ IMPLEMENTACIJU");
                }
            }
        });
    }
}
