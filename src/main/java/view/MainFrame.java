package view;

import controller.FilterController;
import controller.ImportController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    JButton importDugme;
    FilterController filterController;
    JButton exportDugme;
    JButton editDugme;
    JButton changeDugme;
    JButton filterDugme;

    JTable tabelaRasporeda;

    JRadioButton imp1RB;
    JRadioButton imp2RB;
    JTextArea filterTA;
    ImportController importController;

    JScrollPane jScrollPane;

    DefaultTableModel model;


    public MainFrame(){
        initialise();
    }

    public void initialise(){
        this.setTitle("SK Projekat 1 - Univerzalni Raspored");
        this.setSize(900,750);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());

        this.setLayout(new BorderLayout());

        importDugme = new JButton("Import");
        exportDugme = new JButton("Export");
        editDugme = new JButton("Edit view");
        changeDugme = new JButton("Add/Remove/Change");
        filterDugme = new JButton("Filtriraj");

        importDugme.setFocusable(false);
        editDugme.setFocusable(false);
        changeDugme.setFocusable(false);
        exportDugme.setFocusable(false);


        importController = new ImportController(this);




        model = new DefaultTableModel();

        tabelaRasporeda = new JTable(model);




        imp1RB = new JRadioButton("Format 1");
        imp2RB = new JRadioButton("Format 2");

        ButtonGroup izborImplementacije = new ButtonGroup();
        izborImplementacije.add(imp1RB);
        izborImplementacije.add(imp2RB);

        filterTA = new JTextArea();
        filterTA.setSize(25,10);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();


        panel1.setBackground(Color.BLUE);
        panel2.setBackground(Color.BLACK);
        panel3.setBackground(Color.GREEN);
        panel4.setBackground(Color.GRAY);
        panel5.setBackground(Color.MAGENTA);


        panel1.setPreferredSize(new Dimension(130,130));
        panel2.setPreferredSize(new Dimension(130,130));
        panel3.setPreferredSize(new Dimension(130,130));
        panel4.setPreferredSize(new Dimension(130,130));
        panel5.setPreferredSize(new Dimension(400,400));

        panel2.setLayout(new BorderLayout());
        JPanel miniPanel1 = new JPanel();
        JPanel miniPanel2 = new JPanel();
        miniPanel2.setPreferredSize(new Dimension(50,50));
        miniPanel1.setPreferredSize(new Dimension(50,50));
        miniPanel1.add(filterTA);
        miniPanel2.add(filterDugme);
        panel2.add(miniPanel1,BorderLayout.NORTH);
        panel2.add(miniPanel2,BorderLayout.CENTER);



        panel4.add(importDugme);
        panel4.add(exportDugme);

        panel1.add(imp1RB);
        panel1.add(imp2RB);



        panel3.add(changeDugme);
        panel3.add(editDugme);

        tabelaRasporeda.setPreferredSize(new Dimension(600,400));

        jScrollPane = new JScrollPane(tabelaRasporeda);

        jScrollPane.setPreferredSize(new Dimension(600,400));
        jScrollPane.setVisible(true);
        panel5.add(jScrollPane);




        this.add(panel1,BorderLayout.EAST);
        this.add(panel2,BorderLayout.WEST);
        this.add(panel3,BorderLayout.SOUTH);
        this.add(panel4,BorderLayout.NORTH);
        this.add(panel5,BorderLayout.CENTER);




        filterController = new FilterController(this);
        this.setVisible(true);
    }

    public JButton getImportDugme() {
        return importDugme;
    }

    public JButton getExportDugme() {
        return exportDugme;
    }

    public JButton getEditDugme() {
        return editDugme;
    }

    public JButton getChangeDugme() {
        return changeDugme;
    }

    public JButton getFilterDugme() {
        return filterDugme;
    }

    public JTable getTabelaRasporeda() {
        return tabelaRasporeda;
    }

    public JRadioButton getImp1RB() {
        return imp1RB;
    }

    public JRadioButton getImp2RB() {
        return imp2RB;
    }

    public JTextArea getFilterTA() {
        return filterTA;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setImportDugme(JButton importDugme) {
        this.importDugme = importDugme;
    }

    public void setExportDugme(JButton exportDugme) {
        this.exportDugme = exportDugme;
    }

    public void setEditDugme(JButton editDugme) {
        this.editDugme = editDugme;
    }

    public void setChangeDugme(JButton changeDugme) {
        this.changeDugme = changeDugme;
    }

    public void setFilterDugme(JButton filterDugme) {
        this.filterDugme = filterDugme;
    }

    public void setTabelaRasporeda(JTable tabelaRasporeda) {
        this.tabelaRasporeda = tabelaRasporeda;
    }

    public void setImp1RB(JRadioButton imp1RB) {
        this.imp1RB = imp1RB;
    }

    public void setImp2RB(JRadioButton imp2RB) {
        this.imp2RB = imp2RB;
    }

    public void setFilterTA(JTextArea filterTA) {
        this.filterTA = filterTA;
    }

    public void setImportController(ImportController importController) {
        this.importController = importController;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

}
