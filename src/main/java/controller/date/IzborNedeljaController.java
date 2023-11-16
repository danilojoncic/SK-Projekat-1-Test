package controller.date;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IzborNedeljaController {
    private MainFrame mainFrame;


    public IzborNedeljaController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        attachListeners();

    }

    public void attachListeners(){
        mainFrame.getComboBoxZaNedelje().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!mainFrame.getComboBoxZaNedelje().getSelectedItem().toString().equalsIgnoreCase("Sve")) {
                    int index = mainFrame.getComboBoxZaNedelje().getSelectedIndex();
                    int brojNedelje = index - 1;
                    int pocetniIndeks = brojNedelje * Cuvac.getInstance().getOriginalBrojDogadjaja();
                    int krajnjiIndeks = pocetniIndeks + Cuvac.getInstance().getOriginalBrojDogadjaja() - 1;
                    List<Dogadjaj> sublist = new ArrayList<>(izdvojiPodskup(Cuvac.getInstance().getRaspored().getDogadjaji(), pocetniIndeks, krajnjiIndeks));
                    List<String> header = Cuvac.getInstance().getHeader();


                    String[] kolone = new String[header.size()];
                    kolone = header.toArray(kolone);
                    DefaultTableModel model = new DefaultTableModel(kolone, 0);

                    for (Dogadjaj s : sublist) {
                        List<String> lista = s.getStavkeDogadjaja();
                        String[] red = new String[lista.size()];
                        red = lista.toArray(red);


                        model.addRow(red);
                    }
                    mainFrame.getTabelaRasporeda().setModel(model);
                    mainFrame.getTabelaRasporeda().revalidate();
                    mainFrame.getTabelaRasporeda().repaint();
                }
                else{
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                    mainFrame.getTabelaRasporeda().revalidate();
                    mainFrame.getTabelaRasporeda().repaint();
                }
//                mainFrame.getTabelaRasporeda().removeRowSelectionInterval(0,brojNedelje);
//                mainFrame.getTabelaRasporeda().removeRowSelectionInterval(brojNedelje,Cuvac.getInstance().getOriginalBrojDogadjaja());

            }

            private List<Dogadjaj> izdvojiPodskup(List<Dogadjaj> lista, int pocetniIndeks, int krajnjiIndeks) {

                if (pocetniIndeks < 0 || krajnjiIndeks >= lista.size() || pocetniIndeks > krajnjiIndeks) {
                    throw new IllegalArgumentException("Neispravni indeksi");
                }

                // Koriscenje podskupa
                return lista.subList(pocetniIndeks, krajnjiIndeks + 1);
            }

        });

    }


}
