package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import org.raf.csvimpl1.CSVCitac;
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

                if(response == JFileChooser.APPROVE_OPTION){
                    CSVCitac csvCitac = new CSVCitac();

                    try {

                        //to do za kasnije
                        raspored = csvCitac.citaj(jFileChooser.getSelectedFile().getAbsolutePath());
                        mainFrame.setPutanjaDoTrenutnogRasporeda(jFileChooser.getSelectedFile().getAbsolutePath());
                        mainFrame.setNestoUcitano(true);
                        //RIJESENJE NASEG PROBLEMA SA PONOVNIM CITANJEM SADA JE SVE SELF SUFIFICIENT
                        //ili kako god da se ta rijec pise
                        Cuvac.getInstance().setRaspored(raspored);
                        Cuvac.getInstance().setHeader(raspored.getHeader().getStavkeDogadjaja());
                        System.out.println(raspored);
                        System.out.println(raspored.getHeader().toString());
                        Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().raspored);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
