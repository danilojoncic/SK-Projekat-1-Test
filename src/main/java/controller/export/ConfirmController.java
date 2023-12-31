package controller.export;

import controller.Cuvac;
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
                    StringBuilder stringBuilder = new StringBuilder();
//                    for(String s : Cuvac.getInstance().getRaspored().getTabLimundo().keySet()){
//                        stringBuilder.append(s + ":" + Cuvac.getInstance().getRaspored().getTabLimundo().get(s).getKapacitet());
//                        stringBuilder.append(",");
//                        stringBuilder.append("\n");
//                    }
                    MessageFormat footer = new MessageFormat(stringBuilder.toString());
                    PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                    set.add(OrientationRequested.LANDSCAPE);
                    try {
                        exportFrame.wrapperFrame.mFrame.getTabela().print(JTable.PrintMode.FIT_WIDTH,header,footer,true,set,true);
                        JOptionPane.showMessageDialog(null,"Printed Succesfully");
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(exportFrame.getRbJSON().isSelected()){
                    type = "json";
                    Cuvac.getInstance().getImplementacija().exportujRaspored("filename",type);
                }else if(exportFrame.getRbCSV().isSelected()){
                    type = "csv";
                    Cuvac.getInstance().getImplementacija().exportujRaspored("filename",type);
                }
                exportFrame.dispose();
            }
        });
    }
}
