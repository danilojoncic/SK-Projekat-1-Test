package controller.edit;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import view.EditFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UradiEditUpdateController {
    EditFrame editFrame;

    public UradiEditUpdateController(EditFrame editFrame) {
        this.editFrame = editFrame;
        attachListeners();
    }

    private void attachListeners(){
        editFrame.getPotvrdi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indeksOdSelektovanogOrginala = vratiIndeks();
                List<Integer> mojaListIndeksa = new ArrayList<>();
                List<String> elementi = new ArrayList<>();
                for(int i = 0; i < editFrame.getListaPolja().size();i++){
                    elementi.add(editFrame.getListaPolja().get(i).getText());
                }
                for(int i = 0; i < editFrame.getjCheckBoxes().size();i++){
                    if(editFrame.getjCheckBoxes().get(i).isSelected()){
                        mojaListIndeksa.add(i);
                    }
                }

                Dogadjaj dogadjajNovi = new Dogadjaj(elementi);
                System.out.println("ORGINAL ->" + editFrame.getOrginal() + " NOVI KAO PROMJENA ->" + dogadjajNovi);
                if(editFrame.getOrginal().isClone(dogadjajNovi)){
                    System.out.println("JA SAM KLON");
                    JOptionPane.showMessageDialog(null,"Nista nisi promijenio!");
                    return;
                }
                Cuvac.getInstance().getRaspored().getDogadjaji().set(indeksOdSelektovanogOrginala,dogadjajNovi);
                Raspored rasporedTemporary = Cuvac.getInstance().getRaspored().kloniraj(Cuvac.getInstance().getRaspored());
                if(mojaListIndeksa.isEmpty()){
                    System.out.println("JA SAM PRAZAN");
                    return;
                }else if(rasporedTemporary.idiNaUvidUPonedeljak(dogadjajNovi,mojaListIndeksa)){
                    System.out.println("JA NISAM PRAZAN");
                    //Cuvac.getInstance().getRaspored().getDogadjaji().add(dogadjajNovi);
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(editFrame.getWrapperFrame(),Cuvac.getInstance().getRaspored());

                }else{
                    JOptionPane.showMessageDialog(null,"Termin je zauzet ne moze se staviti");
                }
            }
        });
    }


    private int vratiIndeks(){
        return editFrame.getWrapperFrame().mFrame.getTabela().getSelectedRow();
    }

}
