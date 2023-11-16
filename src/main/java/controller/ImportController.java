package controller;

import model.Specifikacija;
import model.boljeRijesenje.Manager;
import model.boljeRijesenje.Osobine;
import model.boljeRijesenje.Raspored;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ImportController{

    Specifikacija specifikacija;
    MainFrame mainFrame;
    Raspored raspored;

    public ImportController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        importuj();
    }

    public void importuj(){

        mainFrame.getImportDugme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SKENIRAJ");

                if(mainFrame.getImp1RB().isSelected()){
                    System.out.println("USAO ZA 1");
                    try {
                        System.out.println("POKUSAO SAM");
                        Class.forName("raf.paket.Implemetacija2");
                        System.out.println("SVRSIO SAM POSAO");
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    specifikacija = Manager.getSpecifikacija();
                    Cuvac.getInstance().setImplementacija(specifikacija);

                }else if(mainFrame.getImp2RB().isSelected()){
                    System.out.println("IMAM 0");
                    try {
                        System.out.println("USAO ZA 0");
                        Class.forName("org.raf.paket.Implemetacija1");
                        System.out.println("SVRSIO POSAO ZA 0");
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    specifikacija = Manager.getSpecifikacija();
                    Cuvac.getInstance().setImplementacija(specifikacija);
                }else{
                    JOptionPane.showMessageDialog(null,"Bajo moj izaberi implementaciju i idu u 3 p...");
                }
                mainFrame.getImp1RB().setSelected(false);
                mainFrame.getImp2RB().setSelected(false);
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setCurrentDirectory(new File("/Desktop"));
                int response = jFileChooser.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION){
                    //u buduce svaki rad nad modelom bi zahtjevao metode iz implementaicje
                    //implemetacija1.ucitajRaspored(jFileChooser.getSelectedFile().getAbsolutePath().toString());
                    //raspored = implemetacija1.getRaspored();
                    Cuvac.getInstance().setRaspored(Cuvac.getInstance().getImplementacija().ucitajRaspored(jFileChooser.getSelectedFile().getAbsolutePath()));
                    raspored = Cuvac.getInstance().getRaspored();
                    System.out.println(raspored.getDogadjaji());
                    mainFrame.setNestoUcitano(true);
                    Cuvac.getInstance().setRaspored(raspored);
                    Cuvac.getInstance().setHeader(raspored.getHeader().getStavkeDogadjaja());
                    Cuvac.getInstance().setOriginalBrojDogadjaja(Cuvac.getInstance().getRaspored().getDogadjaji().size());
                    System.out.println(raspored);
                    System.out.println(raspored.getHeader().toString());
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().raspored);
                }
                try {
                    citajSaForumaU2Ujutro();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                //initListaTermina(new ArrayList<>());
//                System.out.println("SVE UCIONICE SU ISPOD");
//                for(String s : raspored.getBozePomozi().get(6).keySet()){
//                    System.out.println(s);
//                }
            }
        });
    }

    private void plagijator(){
        HashMap<String,List<String>> zauzetostTerminaZaUcionice = new HashMap<>();
        for(String s :raspored.getBozePomozi().get(6).keySet()){
            //ovo obavezno ide u model
            zauzetostTerminaZaUcionice.put(s,initListaTermina(new ArrayList<>()));
            for(int i = 0; i < raspored.getBozePomozi().get(6).size();i++){
                //prolazim sa ovim indeksom kroz hashMapu od rasporeda glavnu
                //i kroz hashmapu zauzetosti ucionica
                //ideja je da kada se
            }
        }



//        Cuvac.getInstance().getRaspored().setSlobodniTerminiZaGledanjeGospodaraPrstenovaBluRay(zauzetostTerminaZaUcionice);
    }

    private List<String> initListaTermina(List<String> lista){
        for(int i = 9; i < 22; i++){
            if(i < 10){
                lista.add("0"+i+":00");
                lista.add("0"+i+":15");
                continue;
            }
            lista.add(i+":00");
            lista.add(i+":15");
        }
        return lista;
//        System.out.println("SVI TERMINI DOSTUPNI FAKULTETU");
//        for(String s : lista){
//            System.out.println(s);
//        }

    }


    public void citajSaForumaU2Ujutro() throws FileNotFoundException {
        File file = new File("RAF ucionice.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            HashMap<String,Osobine> tabKupujemProdajem = new HashMap<>();
            while((s = bufferedReader.readLine()) != null){
                String niz[] = s.split(" ");
                int indexUcionice = 0;
                int indexBroja = 2;
                int indexBol1 = 3;
                int indexBol2 = 4;
                if(niz.length < 5) {
                    indexBroja = 1;
                    indexBol1 = 2;
                    indexBol2 = 3;
                }
                String tempUcionica = niz[indexUcionice];
                System.out.println(tempUcionica);
                Boolean b1 = (niz[indexBol1].equals("da"))? true : false;
                Boolean b2 = (niz[indexBol2].equals("da"))? true : false;
                Osobine osobine = new Osobine(Integer.parseInt(niz[indexBroja]),b1,b2);
                tabKupujemProdajem.put(tempUcionica,osobine);
            };
            Cuvac.getInstance().getRaspored().setTabLimundo(tabKupujemProdajem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
