package controller;

import view.DateFrame;
import view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;


    DeleteColumnController deleteCollumnController;
    ExportController exportController;
    ImportController importController;
    FilterController filterController;

    DateController dateController;
    DeleteDogadjajController deleteDogadjajController;
    RefreshController refreshController;

    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initOthers();
    }

    private void initOthers(){
        deleteDogadjajController = new DeleteDogadjajController(mainFrame);
        deleteCollumnController = new DeleteColumnController(mainFrame);
        exportController = new ExportController(mainFrame);
        importController = new ImportController(mainFrame);
        filterController = new FilterController(mainFrame);
        dateController = new DateController(mainFrame);
        refreshController = new RefreshController(mainFrame);

    }
}
