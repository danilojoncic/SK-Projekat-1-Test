package controller.export;


import view.WrapperFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportController {
    WrapperFrame wrapperFrame;

    public ExportController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }

    private void attachListeners() {
        wrapperFrame.mFrame.getExportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Raspored raspored;
                if (wrapperFrame.isJesteNestoUciteno())
                    new ExportFrame(wrapperFrame);
                else
                    JOptionPane.showMessageDialog(null,"Prvo uctajte raspored");
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
