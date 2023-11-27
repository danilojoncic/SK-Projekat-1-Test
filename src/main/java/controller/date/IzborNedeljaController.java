package controller.date;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;
import view.WrapperFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IzborNedeljaController {
    private WrapperFrame wrapperFrame;


    public IzborNedeljaController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;

        attachListeners();

    }

    public void attachListeners(){
        wrapperFrame.mFrame.getWeekPreviewComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!wrapperFrame.mFrame.getWeekPreviewComboBox().getSelectedItem().toString().equalsIgnoreCase("Sve")) {
                    int index = wrapperFrame.mFrame.getWeekPreviewComboBox().getSelectedIndex();
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
                    wrapperFrame.mFrame.getTabela().setModel(model);
                    wrapperFrame.mFrame.getTabela().revalidate();
                    wrapperFrame.mFrame.getTabela().repaint();
                }
                else{
                    Ubacivac.getInstance().ubaciBackendUTabelu(wrapperFrame,Cuvac.getInstance().getRaspored());
                    wrapperFrame.mFrame.getTabela().revalidate();
                    wrapperFrame.mFrame.getTabela().repaint();
                }
            }
        });
    }
    private List<Dogadjaj> izdvojiPodskup(List<Dogadjaj> lista, int pocetniIndeks, int krajnjiIndeks) {

        if (pocetniIndeks < 0 || krajnjiIndeks >= lista.size() || pocetniIndeks > krajnjiIndeks) {
            throw new IllegalArgumentException("Neispravni indeksi");
        }

        // Koriscenje podskupa
        return lista.subList(pocetniIndeks, krajnjiIndeks + 1);
    }


}
