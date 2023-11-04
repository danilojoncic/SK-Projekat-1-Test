package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import org.raf.Implemetacija1;
import org.raf.csvimpl1.CSVCitac;
import org.raf.jsonimpl1.JSONCitac;
import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImportController{

    MainFrame mainFrame;
    Raspored raspored;

    public ImportController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        importuj();
    }

    public void importuj(){

        mainFrame.getImportDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setCurrentDirectory(new File("/Desktop"));
                int response = jFileChooser.showOpenDialog(null);
                Implemetacija1 implemetacija1 = new Implemetacija1();
                if(response == JFileChooser.APPROVE_OPTION){
                    //u buduce svaki rad nad modelom bi zahtjevao metode iz implementaicje
                    //implemetacija1.ucitajRaspored(jFileChooser.getSelectedFile().getAbsolutePath().toString());
                    //raspored = implemetacija1.getRaspored();
                    implemetacija1.ucitajRaspored(jFileChooser.getSelectedFile().getAbsolutePath());
                    raspored = implemetacija1.getRaspored();
                    System.out.println(raspored.getDogadjaji());
                    mainFrame.setNestoUcitano(true);
                    Cuvac.getInstance().setRaspored(raspored);
                    Cuvac.getInstance().setHeader(raspored.getHeader().getStavkeDogadjaja());
                    System.out.println(raspored);
                    System.out.println(raspored.getHeader().toString());
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().raspored);
                }
            }
        });
    }
}
