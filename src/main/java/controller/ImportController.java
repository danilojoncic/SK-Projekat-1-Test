package controller;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Osobine;
import model.boljeRijesenje.Raspored;

import org.raf.Implemetacija1;
import org.raf.csvimpl1.CSVCitac;
import org.raf.jsonimpl1.JSONCitac;
import view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ImportController{

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
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setCurrentDirectory(new File("/Desktop"));
                int response = jFileChooser.showOpenDialog(null);
                Implemetacija1 implemetacija1 = new Implemetacija1();
                if(response == JFileChooser.APPROVE_OPTION){
                    //u buduce svaki rad nad modelom bi zahtjevao metode iz implementaicje
                    //implemetacija1.ucitajRaspored(jFileChooser.getSelectedFile().getAbsolutePath().toString());
                    //raspored = implemetacija1.getRaspored();
                    implemetacija1.ucitajRaspored(jFileChooser.getSelectedFile().getAbsolutePath());
                    raspored = implemetacija1.getRaspored();
                    System.out.println(raspored.getDogadjaji());
                    mainFrame.setNestoUcitano(true);
                    Cuvac.getInstance().setRaspored(raspored);
                    Cuvac.getInstance().setHeader(raspored.getHeader().getStavkeDogadjaja());
                    System.out.println(raspored);
                    System.out.println(raspored.getHeader().toString());
                    Ubacivac.getInstance().ubaciBackendUTabelu(mainFrame,Cuvac.getInstance().raspored);
                }
                citajSaForumaU2Ujutro();
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



        Cuvac.getInstance().getRaspored().setSlobodniTerminiZaGledanjeGospodaraPrstenovaBluRay(zauzetostTerminaZaUcionice);
    }
    private void izbaciZauzete(List<String> lista, int pocetak, int kraj){

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


    public void citajSaForumaU2Ujutro(){
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
