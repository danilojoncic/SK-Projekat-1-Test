package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import org.raf.CSVCitac;
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
                        System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
                        raspored = csvCitac.citaj(jFileChooser.getSelectedFile().getAbsolutePath());
                        //"D:\\Racunarski Fakultet Beograd\\Treca Godina\\5. Semestar\\Softverske komponente\\src\\main\\resources\\csv.csv"
                        System.out.println(raspored);
                        System.out.println(raspored.getHeader().toString());
                        List<String> header = raspored.getHeader().getStavkeDogadjaja();
                       String[] kolone = new String[header.size()];
                       kolone = header.toArray(kolone);

                       DefaultTableModel model = new DefaultTableModel(kolone, 0);


                        int index = 1;
                        for (Dogadjaj s : raspored.getDogadjaji()) {
                            List<String> lista = s.getStavkeDogadjaja();
                            String[] red = new String[lista.size()];
                            red = lista.toArray(red);


                            model.addRow(red);
                            System.out.println(index++);
                        }

                        mainFrame.getTabelaRasporeda().setModel(model);
                       // mainFrame.getTabelaRasporeda().setPreferredScrollableViewportSize(new Dimension(600, 400));

                        mainFrame.revalidate();
                        mainFrame.repaint();



                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
