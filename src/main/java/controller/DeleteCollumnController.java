package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import org.raf.CSVCitac;
import org.raf.CSVPisac;
import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DeleteCollumnController{

    MainFrame mainFrame;

    int index;






    public DeleteCollumnController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();

    }

    private void attachListeners() {
        mainFrame.getEditDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.isNestoUcitano()){
                    index = mainFrame.getComboBox().getSelectedIndex();
                    mainFrame.getTabelaRasporeda().removeColumn(mainFrame.getTabelaRasporeda().getColumnModel().getColumn(index));
                    mainFrame.getComboBox().removeItemAt(index);
                    mainFrame.getTabelaRasporeda().revalidate();
                    mainFrame.getTabelaRasporeda().repaint();
                }
            }
        });
    }





}
