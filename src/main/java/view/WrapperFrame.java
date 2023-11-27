package view;

import controller.ControllManager;

import javax.swing.*;
import java.awt.*;

public class WrapperFrame extends JFrame {
    private ControllManager controllManager;
    private boolean jesteNestoUciteno = false;
    public MFrame mFrame;


    public WrapperFrame() throws HeadlessException {
        init();
    }

    private void init() {
        mFrame = new MFrame();
        this.add(mFrame.$$$getRootComponent$$$());
        this.setTitle("Universal Schedule Viewer");
        this.setSize(new Dimension(800,800));
        this.setVisible(true);
        controllManager = new ControllManager(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ControllManager getControllManager() {
        return controllManager;
    }

    public void setControllManager(ControllManager controllManager) {
        this.controllManager = controllManager;
    }

    public MFrame getmFrame() {
        return mFrame;
    }

    public void setmFrame(MFrame mFrame) {
        this.mFrame = mFrame;
    }

    public boolean isJesteNestoUciteno() {
        return jesteNestoUciteno;
    }

    public void setJesteNestoUciteno(boolean jesteNestoUciteno) {
        this.jesteNestoUciteno = jesteNestoUciteno;
    }
}
