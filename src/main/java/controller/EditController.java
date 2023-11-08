package controller;

import view.AddFrame;
import view.EditFrame;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController {
    MainFrame mainFrame;
    public EditController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    public void attachListeners(){
        mainFrame.getEditPoljeDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.isNestoUcitano()){
                    new EditFrame(mainFrame);
                }else{
                    JOptionPane.showMessageDialog(null,"MICO MORAS PRVO RASPORED DA UCITAS!");
                }
            }
        });
    }
}
