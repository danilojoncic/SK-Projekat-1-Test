package controller;

import controller.add.UpaliAddFrame;
import controller.date.IzborNedeljaController;
import controller.date.UpaliDateFrameController;
import controller.edit.UpaliEditFrameController;
import controller.export.ExportController;
import view.WrapperFrame;

public class ControllManager {
    WrapperFrame wrapperFrame;

    ZauzetostController zauzetostController;

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

    public ControllManager(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;

        initOthers();
    }

    private void initOthers(){
        upaliEditFrameController = new UpaliEditFrameController(wrapperFrame);
        upaliAddFrame = new UpaliAddFrame(wrapperFrame);
        deleteDogadjajController = new DeleteDogadjajController(wrapperFrame);
        deleteCollumnController = new DeleteColumnController(wrapperFrame);
        exportController = new ExportController(wrapperFrame);
        importController = new ImportController(wrapperFrame);
        filterController = new FilterController(wrapperFrame);
        upaliDateFrameController = new UpaliDateFrameController(wrapperFrame);
        refreshController = new RefreshController(wrapperFrame);
        implementacijaController = new ImplementacijaController(wrapperFrame);
        boschSDSPlusController = new IzborNedeljaController(wrapperFrame);
        zauzetostController = new ZauzetostController(wrapperFrame);
    }
}
