package controller;

import view.GanttChartExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlobodnostController {
    GanttChartExample ganttChartExample;

    public SlobodnostController(GanttChartExample ganttChartExample) {
        this.ganttChartExample = ganttChartExample;
        attachListeners();
    }

    private void attachListeners(){
        ganttChartExample.getComboBoxDani().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pick = (String) ganttChartExample.getComboBoxDani().getSelectedItem();
                ganttChartExample.setPrviUslov(pick);
                ganttChartExample.refresh();
            }
        });
    }
}
