package view;

import com.toedter.calendar.JDateChooser;
import controller.Cuvac;
import controller.ImportController;
import controller.Ubacivac;
import model.boljeRijesenje.Datumi;
import model.boljeRijesenje.Dogadjaj;

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

public class AddFrame extends JFrame {

    MainFrame mainFrame;
    JButton krajniDatum;
    List<JCheckBox> jCheckBoxes;
    JButton ucitajDatumeDugme;
    List<JTextField> listaPolja;
    private ArrayList<String> dani = new ArrayList<>();
    private List<JComboBox> jComboBoxes;
    JDateChooser jDateChooser;
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
        this.setSize(750,400);
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
        potvrdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> mojaListIndeksa = new ArrayList<>();
                List<String> elementi = new ArrayList<>();
                for(int i = 0; i < jComboBoxes.size();i++){
                    if(jCheckBoxes.get(i).isSelected()){
                        mojaListIndeksa.add(i);
                    }
                    if(listaPolja.get(i).getText().equals(" ")){
                        elementi.add((String)jComboBoxes.get(i).getSelectedItem());
                    }else{
                        elementi.add(listaPolja.get(i).getText());
                    }
                }
                Dogadjaj dogadjaj = new Dogadjaj(elementi);
                if(Cuvac.getInstance().getRaspored().idiNaUvidUPonedeljak(dogadjaj,mojaListIndeksa)){
                    Cuvac.getInstance().getRaspored().getDogadjaji().add(dogadjaj);
                    Cuvac.getInstance().getRaspored().refresh(Cuvac.getInstance().getRaspored().getDogadjaji());
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().getRaspored());
                }else{
                    JOptionPane.showMessageDialog(null,"Termin je zauzet ne moze se dodati");
                }
            }
        });
        this.setVisible(true);
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
