package controller.date;

import view.DateFrame;
import view.WrapperFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpaliDateFrameController {
    WrapperFrame wrapperFrame;

    public UpaliDateFrameController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();


    }
    public void attachListeners(){

//        mainFrame.getPostaviDatum().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(mainFrame.isNestoUcitano())
//                    new DateFrame(mainFrame);
//                else
//                    JOptionPane.showMessageDialog(null,"Ucitajte prvo raspored");
//            }
//        });
    }


    public WrapperFrame getWrapperFrame() {
        return wrapperFrame;
    }

    public void setWrapperFrame(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
    }
}
