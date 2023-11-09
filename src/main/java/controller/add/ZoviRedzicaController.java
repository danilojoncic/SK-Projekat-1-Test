package controller.add;

import controller.Cuvac;
import controller.Ubacivac;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;
import controller.add.AddFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ZoviRedzicaController {
    AddFrame addFrame;
    public ZoviRedzicaController(AddFrame addFrame){
        this.addFrame = addFrame;
        attachListeners();
    }


    private void attachListeners(){
        addFrame.getPotvrdi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> mojaListIndeksa = new ArrayList<>();
                List<String> elementi = new ArrayList<>();
                for(int i = 0; i < addFrame.getjComboBoxes().size();i++){
                    if(addFrame.getjCheckBoxes().get(i).isSelected()){
                        mojaListIndeksa.add(i);
                    }


                    if(addFrame.getListaPolja().get(i).getText().isBlank()){
                        elementi.add(addFrame.getjComboBoxes().get(i).getSelectedItem().toString());
                        //System.out.println("index i : " + i + " " + jComboBoxes.get(i).getSelectedItem().toString());
                    }else{
                        elementi.add(addFrame.getListaPolja().get(i).getText());
                    }
                }
                //System.out.println("LISTA ELEMENATA ->>> " + elementi);
                Dogadjaj dogadjaj = new Dogadjaj(elementi);


                //ovo treba ispratiti da li valja
                //System.out.println("PRIJE PROVJERE");
                //System.out.println("Dogadjaj ->>>> " + dogadjaj.toString());
                //System.out.println("Lista indeksa ->>>>> " + mojaListIndeksa);
                //rasporedTemporary.idiNaUvidUPonedeljak(dogadjaj,mojaListIndeksa);
                System.out.println(mojaListIndeksa);
                System.out.println(dogadjaj);
                Raspored rasporedTemporary = Cuvac.getInstance().getRaspored().kloniraj(Cuvac.getInstance().getRaspored());
                if(rasporedTemporary.idiNaUvidUPonedeljak(dogadjaj,mojaListIndeksa)){
                    Cuvac.getInstance().getRaspored().getDogadjaji().add(dogadjaj);
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(addFrame.getMainFrame(),Cuvac.getInstance().getRaspored());

                }else{
                    JOptionPane.showMessageDialog(null,"Termin je zauzet ne moze se dodati");
                }
            }
        });
    }
}
