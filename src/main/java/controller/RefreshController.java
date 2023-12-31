package controller;

import model.boljeRijesenje.Dogadjaj;
import org.raf.Main;
import view.WrapperFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RefreshController {
   WrapperFrame wrapperFrame;

    public RefreshController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }

    private void attachListeners(){
        //koristiiti samo nakon zadovoljenog filtriranja
        this.wrapperFrame.mFrame.getApplyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(mainFrame.getImp1RB().isSelected()){
//
//                }else if(mainFrame.getImp2RB().isSelected()){
//
//                }
                String text = Cuvac.getInstance().getFilterString();
                List<Dogadjaj> filtriraniDogadjaji =  Cuvac.getInstance().getRaspored().vratiFiltrirano(text);
                Cuvac.getInstance().getImplementacija().pretraziRaspred(text);
                Cuvac.getInstance().setOriginalBrojDogadjaja(filtriraniDogadjaji.size());
            }
        });
    }
}
