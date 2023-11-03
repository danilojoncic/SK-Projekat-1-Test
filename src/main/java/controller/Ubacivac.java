package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Ubacivac {
    private static Ubacivac instance = null;


    private Ubacivac(){

    }


    public static Ubacivac getInstance(){
        if(instance == null){
            instance = new Ubacivac();
        }
        return instance;
    }



    public void ubaciBackendUTabelu(MainFrame mainFrame, Raspored raspored){
        List<String> header = raspored.getHeader().getStavkeDogadjaja();
        String[] kolone = new String[header.size()];
        kolone = header.toArray(kolone);
        mainFrame.getComboBox().setVisible(true);
        for (String s : header) {
            mainFrame.getComboBox().addItem(s);

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
        mainFrame.getTabelaRasporeda().setModel(model);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
