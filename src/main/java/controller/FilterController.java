package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
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
                String text = mainFrame.getFilterTA().getText();
                Cuvac.getInstance().setFilterString(text);
                mainFrame.getFilterTA().setText("");
                Raspored raspored = Cuvac.getInstance().raspored;
                if(mainFrame.isNestoUcitano()){
                    //rijesenje

                    List<String> header = Cuvac.getInstance().getHeader();


                    String[] kolone = new String[header.size()];
                    kolone = header.toArray(kolone);
                    DefaultTableModel model = new DefaultTableModel(kolone, 0);


                    int index = 1;
                    for (Dogadjaj s : raspored.getDogadjaji()) {
                        boolean flag = false;
                        for (String s1 : s.getStavkeDogadjaja()) {
                            if(s1.contains(text))
                                flag = true;
                        }
                        if(flag){
                            List<String> lista = s.getStavkeDogadjaja();
                            String[] red = new String[lista.size()];
                            red = lista.toArray(red);


                            model.addRow(red);
                            System.out.println(index++);
                        }

                    }
                    mainFrame.getTabelaRasporeda().setModel(model);
                    mainFrame.getTabelaRasporeda().revalidate();
                    mainFrame.getTabelaRasporeda().repaint();
//                    raspored.refresh(raspored.vratiFiltrirano(text));
//                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,raspored);
                }
            }
        });
    }

}
