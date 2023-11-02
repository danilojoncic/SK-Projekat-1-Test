package controller;

import view.DateFrame;
import view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;


    DeleteCollumnController deleteCollumnController;
    ExportController exportController;
    ImportController importController;
    FilterController filterController;

    DateController dateController;

    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initOthers();
    }

    private void initOthers(){
        deleteCollumnController = new DeleteCollumnController(mainFrame);
        exportController = new ExportController(mainFrame);
        importController = new ImportController(mainFrame);
        filterController = new FilterController(mainFrame);
        dateController = new DateController(mainFrame);


    }
}
