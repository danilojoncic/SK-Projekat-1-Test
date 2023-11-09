package controller.add;

import controller.Cuvac;
import controller.Ubacivac;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddFrame extends JFrame {

    MainFrame mainFrame;
    List<JCheckBox> jCheckBoxes;
    List<JTextField> listaPolja;
    ZoviRedzicaController zoviRedzicaController;
    private ArrayList<String> dani = new ArrayList<>();
    private List<JComboBox> jComboBoxes;

    JButton potvrdi;

    public AddFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        initialise();
    }

    public void initialise(){
        jCheckBoxes = new ArrayList<>();
        jComboBoxes = new ArrayList<>();
        listaPolja = new ArrayList<>();
        this.setTitle("Dodaj dogadjaj");
        this.setSize(750,550);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        int i = 0;
        for (String s : Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja()) {
            Label label = new Label(s);
            JCheckBox jCheckBox = new JCheckBox();
            JComboBox jComboBox = new JComboBox<>();
            zoviRedzica(jComboBox,i);
            //jComboBox.addItem((Component) Cuvac.getInstance().getRaspored().getBozePomozi().get(i).keySet());
            JTextField jTextField = new JTextField(" ");
            panel.add(label);
            panel.add(jComboBox);
            panel.add(jTextField);
            panel.add(jCheckBox);
            listaPolja.add(jTextField);
            jComboBoxes.add(jComboBox);
            jCheckBoxes.add(jCheckBox);
            i++;
        }
        potvrdi = new JButton("Potvrdi");
        panel.add(potvrdi);
        this.add(panel);
        //this.pack();
        zoviRedzicaController = new ZoviRedzicaController(this);
        this.setVisible(true);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public List<JCheckBox> getjCheckBoxes() {
        return jCheckBoxes;
    }

    public void setjCheckBoxes(List<JCheckBox> jCheckBoxes) {
        this.jCheckBoxes = jCheckBoxes;
    }

    public List<JTextField> getListaPolja() {
        return listaPolja;
    }

    public void setListaPolja(List<JTextField> listaPolja) {
        this.listaPolja = listaPolja;
    }

    public ArrayList<String> getDani() {
        return dani;
    }

    public void setDani(ArrayList<String> dani) {
        this.dani = dani;
    }

    public List<JComboBox> getjComboBoxes() {
        return jComboBoxes;
    }

    public void setjComboBoxes(List<JComboBox> jComboBoxes) {
        this.jComboBoxes = jComboBoxes;
    }

    public JButton getPotvrdi() {
        return potvrdi;
    }

    public void setPotvrdi(JButton potvrdi) {
        this.potvrdi = potvrdi;
    }

    public void zoviRedzica(JComboBox jComboBox,int index){
        jComboBox.addItem(" ");
        for(String s : Cuvac.getInstance().getRaspored().getBozePomozi().get(index).keySet()){
            if(Ubacivac.getInstance().postojiUComboBox(jComboBox,s)){
                continue;
            }
            jComboBox.addItem(s);
            jComboBox.setSelectedItem(" ");
        }
    }

}
