package controller;

import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteColumnController {

    MainFrame mainFrame;

    public DeleteColumnController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners() {
        mainFrame.getEditDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.isNestoUcitano()) {
                    int index = mainFrame.getComboBox().getSelectedIndex();
                    String s1 = (String)mainFrame.getComboBox().getSelectedItem();
                    System.out.println(s1);
                    int i = 0;
                    for (String s : Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja()) {
                        if(s.equalsIgnoreCase(s1)){
                            Cuvac.getInstance().getRaspored().obrisiKolonu(i);
                            Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja().remove(i);
                            Cuvac.getInstance().getHeader().remove(s1);
                            System.out.println(Cuvac.getInstance().getRaspored().getHeader());
                            break;
                        }
                        i++;
                    }
                    //Cuvac.getInstance().getImplementacija().sacuvajRaspored();
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                    mainFrame.getComboBox().removeItem(s1);
                }
            }
        });
    }
}
