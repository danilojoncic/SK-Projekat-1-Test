package controller;

import controller.Cuvac;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.raf.csvimpl1.CSVPisac;
import org.raf.jsonimpl1.JSONPIsac;
import view.ExportFrame;
import view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.awt.Font.BOLD;
import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;

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
                if (exportFrame.getRbPDF().isSelected()) {
                    BufferedImage bi = new BufferedImage(500,500,TYPE_3BYTE_BGR);
                    Graphics g = bi.createGraphics();
                    exportFrame.getMainFrame().getTabelaRasporeda().paint(g);
                    g.dispose();
                    File file = new File("output.pdf");
                    try {
                        ImageIO.write(bi, "pdf", file);

                        // ukoliko korisnik nije stavio da je slika .png, postavice sam
                        if (!file.getName().endsWith(".pdf")) {
                            file.renameTo(new File(file.getPath() + ".pdf"));
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (exportFrame.getRbCSV().isSelected()) {
                    Raspored raspored = Cuvac.getInstance().getRaspored();
                    List<String> header = Cuvac.getInstance().getHeader();
                    String[] kolone = new String[header.size()];
                    CSVPisac csvPisac = new CSVPisac();
                    csvPisac.napisi(raspored);
                } else if (exportFrame.getRbJSON().isSelected()) {
                    Raspored raspored = Cuvac.getInstance().getRaspored();
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
