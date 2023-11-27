package controller.edit;

import view.EditFrame;
import view.WrapperFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpaliEditFrameController {
    WrapperFrame wrapperFrame;
    public UpaliEditFrameController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }

    public void attachListeners(){
        wrapperFrame.mFrame.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wrapperFrame.isJesteNestoUciteno()){
                    new EditFrame(wrapperFrame);
                }else{
                    JOptionPane.showMessageDialog(null,"MICO MORAS PRVO RASPORED DA UCITAS!");
                }
            }
        });
    }
}
