package controller.export;

import controller.Cuvac;

import org.raf.Implemetacija1;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                Implemetacija1 implemetacija1 = new Implemetacija1();
                implemetacija1.setRaspored(Cuvac.getInstance().getRaspored());
                implemetacija1.getRaspored().setHeader(Cuvac.getInstance().getRaspored().getHeader());
                String type;
                String fileName;


                if (exportFrame.getRbPDF().isSelected()) {
                    type = "pdf";
                    //mali bag za datume koji se rijesava na komponenti koja radi sa datumima
                    Date od = Cuvac.getInstance().getRaspored().getDatumOdKadaVazi();
                    Date doV = Cuvac.getInstance().getRaspored().getDatumDoKadaVazi();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.y");
                    String odDaValja = simpleDateFormat.format(od);
                    String doDaValja = simpleDateFormat.format(doV);
                    MessageFormat header = new MessageFormat("Raspored " +
                               odDaValja +  "- " + doDaValja);

                    PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(OrientationRequested.LANDSCAPE);
                    try {
                        exportFrame.getMainFrame().getTabelaRasporeda().print(JTable.PrintMode.FIT_WIDTH,header,null,true,set,true);
                        JOptionPane.showMessageDialog(null,"Printed Succesfully");
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(exportFrame.getRbJSON().isSelected()){
                    type = "json";
                    implemetacija1.kreirajRaspored("filename",type);
                }else if(exportFrame.getRbCSV().isSelected()){
                    type = "csv";
                    implemetacija1.kreirajRaspored("output.csv",type);
                }
                exportFrame.dispose();
            }
        });
    }
}
