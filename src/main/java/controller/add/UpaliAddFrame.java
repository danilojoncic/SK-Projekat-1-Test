package controller.add;

import view.AddFrame;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpaliAddFrame {
    MainFrame mainFrame;
    public UpaliAddFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    public void attachListeners(){
        mainFrame.getChangeDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.isNestoUcitano()){
                    new AddFrame(mainFrame);
                }else{
                    JOptionPane.showMessageDialog(null,"MICO MORAS PRVO RASPORED DA UCITAS!");
                }
            }
        });
    }
}
