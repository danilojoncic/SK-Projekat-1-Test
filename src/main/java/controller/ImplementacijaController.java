package controller;

import view.DateFrame;
import model.Specifikacija;
import view.WrapperFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImplementacijaController {
    WrapperFrame wrapperFrame;
    Specifikacija specifikacija;

    public ImplementacijaController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }


    private void attachListeners(){
        wrapperFrame.mFrame.getFormatImplementationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wrapperFrame.mFrame.getFirstRadioButton().isSelected()){
                    Cuvac.getInstance().getRaspored().nejmarUPetercu(1);
                    Ubacivac.getInstance().ubaciBackendUTabelu(wrapperFrame,Cuvac.getInstance().getRaspored());
                    new DateFrame(wrapperFrame);
                }else if(wrapperFrame.mFrame.getSecondRadioButton().isSelected()){
                    Cuvac.getInstance().getRaspored().nejmarUPetercu(0);
                    Ubacivac.getInstance().ubaciBackendUTabelu(wrapperFrame,Cuvac.getInstance().getRaspored());
                    new DateFrame(wrapperFrame);
                }else{
                    JOptionPane.showMessageDialog(null,"IGRAM IGRAM VRTIM SE KO CIGRA< BAJO SELEKTUJ IMPLEMENTACIJU");
                }
            }
        });
    }
}
