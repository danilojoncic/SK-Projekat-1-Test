package controller;

import controller.Cuvac;
import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteColumnController {

    MainFrame mainFrame;

    public DeleteColumnController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners() {
        mainFrame.getEditDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.isNestoUcitano()) {
                    int index = mainFrame.getComboBox().getSelectedIndex();

                    if (index != -1) {
                        // Delete the selected column from the JTable's model
                        DefaultTableModel model = (DefaultTableModel) mainFrame.getTabelaRasporeda().getModel();
                        model.setColumnCount(model.getColumnCount() - 1);

                        // Remove the item from the combo box
                        mainFrame.getComboBox().removeItemAt(index);

                        // Update your data structure to reflect the deletion
                        Cuvac.getInstance().getRaspored().obrisiKolonu(index);

                        // Revalidate and repaint the JTable
                        mainFrame.getTabelaRasporeda().revalidate();
                        mainFrame.getTabelaRasporeda().repaint();
                    } else {
                        // Handle the case where no column is selected
                        JOptionPane.showMessageDialog(null, "Please select a column to delete.");
                    }
                }
            }
        });
    }
}
