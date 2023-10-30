package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import org.raf.CSVCitac;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class FilterController {
    MainFrame mainFrame;

    public FilterController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners(){
        mainFrame.getFilterDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Raspored raspored;
                String text = mainFrame.getFilterTA().getText();
                CSVCitac csvCitac = new CSVCitac();
                try {
                    raspored = csvCitac.citaj("C:\\Users\\jonci\\Desktop\\neBrisati.csv");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                List<String> header = raspored.getHeader().getStavkeDogadjaja();
                String[] kolone = new String[header.size()];
                kolone = header.toArray(kolone);
                List<Dogadjaj> dogs = raspored.vratiFiltrirano(text);
                try {
                    raspored = csvCitac.refreshRaspored(dogs);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel model = new DefaultTableModel(kolone, 0);


                int index = 1;
                for (Dogadjaj s : raspored.getDogadjaji()) {
                    List<String> lista = s.getStavkeDogadjaja();
                    String[] red = new String[lista.size()];
                    red = lista.toArray(red);


                    model.addRow(red);
                    System.out.println(index++);
                }
                mainFrame.getTabelaRasporeda().revalidate();

                mainFrame.getTabelaRasporeda().setModel(model);
                // mainFrame.getTabelaRasporeda().setPreferredScrollableViewportSize(new Dimension(600, 400));

                mainFrame.revalidate();
                mainFrame.repaint();

            }
        });
    }

}
