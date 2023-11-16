package controller;

import controller.add.UpaliAddFrame;
import controller.date.IzborNedeljaController;
import controller.date.UpaliDateFrameController;
import controller.edit.UpaliEditFrameController;
import controller.export.ExportController;
import view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;


    DeleteColumnController deleteCollumnController;
    ExportController exportController;
    ImportController importController;
    FilterController filterController;
    UpaliEditFrameController upaliEditFrameController;

    UpaliDateFrameController upaliDateFrameController;
    DeleteDogadjajController deleteDogadjajController;
    UpaliAddFrame upaliAddFrame;
    RefreshController refreshController;
    ImplementacijaController implementacijaController;

    IzborNedeljaController boschSDSPlusController;

    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initOthers();
    }

    private void initOthers(){
        upaliEditFrameController = new UpaliEditFrameController(mainFrame);
        upaliAddFrame = new UpaliAddFrame(mainFrame);
        deleteDogadjajController = new DeleteDogadjajController(mainFrame);
        deleteCollumnController = new DeleteColumnController(mainFrame);
        exportController = new ExportController(mainFrame);
        importController = new ImportController(mainFrame);
        filterController = new FilterController(mainFrame);
        upaliDateFrameController = new UpaliDateFrameController(mainFrame);
        refreshController = new RefreshController(mainFrame);
        implementacijaController = new ImplementacijaController(mainFrame);
        boschSDSPlusController = new IzborNedeljaController(mainFrame);
    }
}
