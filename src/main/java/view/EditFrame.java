package view;

import controller.Cuvac;
import controller.Ubacivac;
import controller.edit.UradiEditUpdateController;
import model.boljeRijesenje.Dogadjaj;
import view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EditFrame extends JFrame {

    MainFrame mainFrame;
    List<JCheckBox> jCheckBoxes;
    List<JTextField> listaPolja;
    Dogadjaj orginal;
    private ArrayList<String> dani = new ArrayList<>();
    UradiEditUpdateController uradiEditUpdateController;

    JButton potvrdi;

    public EditFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        initialise();
    }

    public void initialise(){
        jCheckBoxes = new ArrayList<>();
        listaPolja = new ArrayList<>();
        this.setTitle("Promijeni dogadjaj");
        this.setSize(750,550);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        int i = 0;
        for (String s : Cuvac.getInstance().getRaspored().getHeader().getStavkeDogadjaja()) {
            JCheckBox jCheckBox = new JCheckBox();
            JTextField jTextField = new JTextField(" ");
            panel.add(jTextField);
            panel.add(jCheckBox);
            listaPolja.add(jTextField);
            jCheckBoxes.add(jCheckBox);
            i++;
        }
        int indeksOdSelektovanogOrginala = vratiIndeks();
        orginal = Cuvac.getInstance().getRaspored().getDogadjaji().get(indeksOdSelektovanogOrginala);
        initFields();
        potvrdi = new JButton("Potvrdi");
        panel.add(potvrdi);
        this.add(panel);
        this.setVisible(true);
        uradiEditUpdateController = new UradiEditUpdateController(this);
    }


    private void initFields(){
        Dogadjaj dogadjaj;
        dogadjaj = Cuvac.getInstance().getRaspored().getDogadjaji().get(mainFrame.getTabelaRasporeda().getSelectedRow());
        for(int i = 0; i < dogadjaj.getStavkeDogadjaja().size();i++){
            listaPolja.get(i).setText(dogadjaj.getStavkeDogadjaja().get(i));
        }
    }
    private int vratiIndeks(){
        return mainFrame.getTabelaRasporeda().getSelectedRow();
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

    public Dogadjaj getOrginal() {
        return orginal;
    }

    public void setOrginal(Dogadjaj orginal) {
        this.orginal = orginal;
    }

    public ArrayList<String> getDani() {
        return dani;
    }

    public void setDani(ArrayList<String> dani) {
        this.dani = dani;
    }

    public JButton getPotvrdi() {
        return potvrdi;
    }

    public void setPotvrdi(JButton potvrdi) {
        this.potvrdi = potvrdi;
    }
}
