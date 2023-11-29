package controller;


import view.GanttChartExample;
import view.WrapperFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZauzetostController {
    private WrapperFrame wrapperFrame;

    public ZauzetostController(WrapperFrame wrapperFrame) {
        this.wrapperFrame = wrapperFrame;
        attachListeners();
    }




    public void attachListeners(){
        wrapperFrame.mFrame.getCheckAvailabilityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GanttChartExample("Gantt");
            }
        });
    }
}
