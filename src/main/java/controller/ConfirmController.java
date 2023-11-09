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
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
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
                    //mali bag za datume koji se rijesava na komponenti koja radi sa datumima
                    Date od = Cuvac.getInstance().getRaspored().getDatumOdKadaVazi();
                    Date doV = Cuvac.getInstance().getRaspored().getDatumDoKadaVazi();
                    MessageFormat header = new MessageFormat("Raspored " +
                              od.getDay() + "." + od.getMonth() + ". " +  "- " + doV.getDay() + "." + doV.getMonth());

                    PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(OrientationRequested.LANDSCAPE);
                    try {
                        exportFrame.getMainFrame().getTabelaRasporeda().print(JTable.PrintMode.FIT_WIDTH,header,null,true,set,true);
                        JOptionPane.showMessageDialog(null,"Printed Succesfully");
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                exportFrame.dispose();
            }
        });
    }
}
