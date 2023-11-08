package view;

import controller.ConfirmController;
import controller.ExportController;
import controller.FilterController;
import controller.ImportController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExportFrame extends JFrame {

    JRadioButton rbCSV = new JRadioButton("CSV");
    JRadioButton rbPDF = new JRadioButton("PDF");
    JRadioButton rbJSON = new JRadioButton("JSON");

    JButton confirm = new JButton("Confirm");
    ButtonGroup buttonGroup = new ButtonGroup();
    MainFrame mainFrame;
    ConfirmController confirmController;
    public ExportFrame(MainFrame mainFrame){
        initialise();
        this.mainFrame = mainFrame;
    }

    public void initialise(){
        this.setTitle("SK Projekat 1 - Univerzalni Raspored");
        this.setSize(300,100);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());

        this.setLayout(new FlowLayout());

        this.add(rbCSV);
        this.add(rbPDF);
        this.add(rbJSON);

        this.add(confirm);
        confirmController = new ConfirmController(this);

        rbCSV.setSelected(true);

        buttonGroup.add(rbCSV);
        buttonGroup.add(rbPDF);
        buttonGroup.add(rbJSON);


        this.setVisible(true);
    }

    public JRadioButton getRbCSV() {
        return rbCSV;
    }

    public void setRbCSV(JRadioButton rbCSV) {
        this.rbCSV = rbCSV;
    }

    public JRadioButton getRbPDF() {
        return rbPDF;
    }

    public void setRbPDF(JRadioButton rbPDF) {
        this.rbPDF = rbPDF;
    }

    public JRadioButton getRbJSON() {
        return rbJSON;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setRbJSON(JRadioButton rbJSON) {
        this.rbJSON = rbJSON;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public void setConfirm(JButton confirm) {
        this.confirm = confirm;
    }
}
