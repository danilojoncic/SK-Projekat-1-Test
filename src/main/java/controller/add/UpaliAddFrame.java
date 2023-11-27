package controller.add;

import view.AddFrame;

import view.WrapperFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpaliAddFrame {
    WrapperFrame wrapperFrame;
    public UpaliAddFrame(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }

    public void attachListeners(){
        wrapperFrame.mFrame.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wrapperFrame.isJesteNestoUciteno()){
                    new AddFrame(wrapperFrame);
                }else{
                    JOptionPane.showMessageDialog(null,"MICO MORAS PRVO RASPORED DA UCITAS!");
                }
            }
        });
    }
}
