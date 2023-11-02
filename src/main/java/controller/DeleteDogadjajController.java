package controller;

import model.boljeRijesenje.Dogadjaj;
import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDogadjajController {

    MainFrame mainFrame;
    public DeleteDogadjajController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }


    private void attachListeners(){
        this.mainFrame.getObrisiDogadjajDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.isNestoUcitano()) {
                    JTable tabelaRasporeda = mainFrame.getTabelaRasporeda();
                    int selectedRow = tabelaRasporeda.getSelectedRow();

                    if (selectedRow != -1) {
                        DefaultTableModel model = (DefaultTableModel) tabelaRasporeda.getModel();
                        model.removeRow(selectedRow);
                        Cuvac.getInstance().getRaspored().obrisiRed(selectedRow);
                        tabelaRasporeda.revalidate();
                        tabelaRasporeda.repaint();
                        for(Dogadjaj dogadjaj : Cuvac.getInstance().getRaspored().getDogadjaji()){
                            System.out.println(dogadjaj);
                        }
                        System.out.println("--------------------------------------------");
                    } else {
                        // Handle the case where no row is selected
                        JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "NE MOZE MICOOOO");
                }
            }

        });
    }
}
