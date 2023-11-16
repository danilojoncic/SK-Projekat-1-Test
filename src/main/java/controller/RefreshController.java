package controller;

import model.boljeRijesenje.Dogadjaj;
import org.raf.Main;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RefreshController {
    MainFrame mainFrame;

    public RefreshController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners(){
        //koristiiti samo nakon zadovoljenog filtriranja
        this.mainFrame.getRefreshDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.getImp1RB().isSelected()){

                }else if(mainFrame.getImp2RB().isSelected()){

                }



                String text = mainFrame.getFilterTA().getText();
                List<Dogadjaj> filtriraniDogadjaji =  Cuvac.getInstance().getRaspored().vratiFiltrirano(text);
                //Cuvac.getInstance().getRaspored().setDogadjaji(filtriraniDogadjaji);
                Cuvac.getInstance().getImplementacija().pretraziRaspred(text);
                //Cuvac.getInstance().getRaspored().refresh(filtriraniDogadjaji);
                Cuvac.getInstance().setOriginalBrojDogadjaja(filtriraniDogadjaji.size());
            }
        });
    }
}
