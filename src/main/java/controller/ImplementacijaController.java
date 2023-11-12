package controller;

import org.raf.Implemetacija1;
import org.raf.pdf.Imp1Raspored;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImplementacijaController {
    MainFrame mainFrame;

    public ImplementacijaController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }


    private void attachListeners(){
        mainFrame.getImp1RB().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                Imp1Raspored imp1Raspored = new Imp1Raspored(Cuvac.getInstance().getRaspored());
//                Implemetacija1 implemetacija1 = new Implemetacija1();
//                implemetacija1.setImp1Raspored(imp1Raspored);
//                implemetacija1.getImp1Raspored().setRaspored(Cuvac.getInstance().getRaspored());
//                implemetacija1.getImp1Raspored().getRaspored().setHeader(Cuvac.getInstance().getRaspored().getHeader());
//                implemetacija1.getImp1Raspored().nejmarUPetercu();
//                Cuvac.getInstance().setRaspored(implemetacija1.getRaspored());
//                Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
//                mainFrame.repaint();
//                mainFrame.revalidate();
            }
        });

        mainFrame.getImp2RB().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("IDUCI PUT BRATE, NEMOJ SADA!");
            }
        });

    }
}
