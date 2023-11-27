package controller;


import view.WrapperFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteColumnController {

    WrapperFrame wrapperFrame;

    public DeleteColumnController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }

    private void attachListeners() {
        wrapperFrame.mFrame.getDeleteColumnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wrapperFrame.isJesteNestoUciteno()) {
                    int index = wrapperFrame.mFrame.getComboBox1().getSelectedIndex();
                    String s1 = (String)wrapperFrame.mFrame.getComboBox1().getSelectedItem();
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
                    Ubacivac.getInstance().ubaciBackendUTabelu(wrapperFrame,Cuvac.getInstance().getRaspored());
                    wrapperFrame.mFrame.getComboBox1().removeItem(s1);
                }
            }
        });
    }
}
