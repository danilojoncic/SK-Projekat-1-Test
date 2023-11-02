package controller;

import view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;
    DeleteCollumnController deleteCollumnController;
    ExportController exportController;
    ImportController importController;
    FilterController filterController;

    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initOthers();
    }

    private void initOthers(){
        deleteCollumnController = new DeleteCollumnController(mainFrame);
        exportController = new ExportController(mainFrame);
        importController = new ImportController(mainFrame);
        filterController = new FilterController(mainFrame);
    }
}
