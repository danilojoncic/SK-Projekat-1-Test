package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import org.raf.csvimpl1.CSVCitac;
import org.raf.csvimpl1.CSVPisac;
import org.raf.jsonimpl1.JSONPIsac;
import view.ExportFrame;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConfirmController {
    ExportFrame exportFrame;

    public ConfirmController(ExportFrame exportFrame) {
        this.exportFrame = exportFrame;
        attachListeners();
    }

    private void attachListeners() {
        exportFrame.getConfirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Raspored raspored;
                if (exportFrame.getRbCSV().isSelected()) {
                    raspored = Cuvac.getInstance().getRaspored();
                    List<String> header = Cuvac.getInstance().getHeader();
                    String[] kolone = new String[header.size()];
                    CSVPisac csvPisac = new CSVPisac();
                    csvPisac.napisi(raspored);
                }
                if(exportFrame.getRbJSON().isSelected()){
                    raspored = Cuvac.getInstance().getRaspored();
                    JSONPIsac jsonpIsac = new JSONPIsac(raspored);
                    File file = new File("output.json");
                    try {
                        jsonpIsac.ispisiJSON(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                exportFrame.dispose();
            }
        });
    }
}