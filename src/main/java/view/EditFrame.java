package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;
import controller.ImportController;
import controller.Ubacivac;
import model.boljeRijesenje.Datumi;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditFrame extends JFrame {

    MainFrame mainFrame;
    List<JCheckBox> jCheckBoxes;
    List<JTextField> listaPolja;
    Dogadjaj orginal;
    private ArrayList<String> dani = new ArrayList<>();

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
        potvrdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indeksOdSelektovanogOrginala = vratiIndeks();
                List<Integer> mojaListIndeksa = new ArrayList<>();
                List<String> elementi = new ArrayList<>();
                for(int i = 0; i < listaPolja.size();i++){
                    elementi.add(listaPolja.get(i).getText());
                }
                for(int i = 0; i < jCheckBoxes.size();i++){
                    if(jCheckBoxes.get(i).isSelected()){
                        mojaListIndeksa.add(i);
                    }
                }

                Dogadjaj dogadjajNovi = new Dogadjaj(elementi);
                System.out.println("ORGINAL ->" + orginal + " NOVI KAO PROMJENA ->" + dogadjajNovi);
                if(orginal.isClone(dogadjajNovi)){
                    System.out.println("JA SAM KLON");
                    JOptionPane.showMessageDialog(null,"Nista nisi promijenio!");
                    return;
                }
                Cuvac.getInstance().getRaspored().getDogadjaji().set(indeksOdSelektovanogOrginala,dogadjajNovi);
                Raspored rasporedTemporary = Cuvac.getInstance().getRaspored().kloniraj(Cuvac.getInstance().getRaspored());
                if(mojaListIndeksa.isEmpty()){
                    System.out.println("JA SAM PRAZAN");
                    return;
                }else if(rasporedTemporary.idiNaUvidUPonedeljak(dogadjajNovi,mojaListIndeksa)){
                    System.out.println("JA NISAM PRAZAN");
                    //Cuvac.getInstance().getRaspored().getDogadjaji().add(dogadjajNovi);
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());

                }else{
                    JOptionPane.showMessageDialog(null,"Termin je zauzet ne moze se staviti");
                }
            }
        });
        this.setVisible(true);
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
}
