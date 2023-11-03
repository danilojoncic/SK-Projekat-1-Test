package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import org.raf.csvimpl1.CSVCitac;
import org.raf.csvimpl1.CSVPisac;
import view.ExportFrame;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ExportController {
    MainFrame mainFrame;

    public ExportController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners() {
        mainFrame.getExportDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Raspored raspored;
                if (mainFrame.isNestoUcitano())
                    new ExportFrame();
//                CSVCitac csvCitac = new CSVCitac();
//                if (mainFrame.isNestoUcitano()) {
//                    try {
//                        raspored = csvCitac.citaj(mainFrame.getPutanjaDoTrenutnogRasporeda());
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//
//                    List<String> header = raspored.getHeader().getStavkeDogadjaja();
//                    String[] kolone = new String[header.size()];
//                    kolone = header.toArray(kolone);
//                    List<Dogadjaj> dogs = raspored.vratiFiltrirano(text);
//                    if (!(dogs == null)) {
//                        raspored = raspored.refresh(dogs);
//                        CSVPisac csvPisac = new CSVPisac();
//                        csvPisac.napisi(raspored);
//                    }
//                }


            }
        });
    }
}
