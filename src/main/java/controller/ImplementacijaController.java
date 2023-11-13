package controller;

import controller.date.DateFrame;
import org.raf.Implemetacija1;
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
                System.out.println("Pritisao impl1 dugme");
                Implemetacija1 implemetacija1 = new Implemetacija1();
                implemetacija1.setRaspored(Cuvac.getInstance().getRaspored());
                implemetacija1.nejmarUPetercu();
                Cuvac.getInstance().setRaspored(implemetacija1.getRaspored());
                Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                //nema smisla da diramo ovo jer bi bilo glupo ako smo formatirali kao impl1
                mainFrame.getComboBox().setVisible(false);
                mainFrame.repaint();
                mainFrame.revalidate();
                new DateFrame(mainFrame);
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
