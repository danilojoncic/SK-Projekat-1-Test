package view;

import controller.Cuvac;
import controller.SlobodnostController;
import javafx.scene.control.ComboBox;
import model.boljeRijesenje.Dogadjaj;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import javax.swing.*;
import javax.swing.text.TabStop;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GanttChartExample extends JFrame {
    private String prviUslov = "PON";
    private JButton refreshChanges;
    private SlobodnostController slobodnostController;

    private JComboBox<String> comboBoxDani;
    private JPanel mainPanel;
    private ChartPanel chartPanel;
    public GanttChartExample(String title) {
        super(title);
        init();
    }

    private void init() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        comboBoxDani = new JComboBox<>();
        refreshChanges = new JButton("Refresh");
        formatirajComboBox(comboBoxDani);
        IntervalCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(comboBoxDani,BorderLayout.NORTH);
        mainPanel.add(refreshChanges,BorderLayout.SOUTH);
        mainPanel.add(chartPanel);
        this.add(mainPanel);
        slobodnostController = new SlobodnostController(this);
        this.setVisible(true);
    }

    public JButton getRefreshChanges() {
        return refreshChanges;
    }

    public void setRefreshChanges(JButton refreshChanges) {
        this.refreshChanges = refreshChanges;
    }

    public void refresh(){
        IntervalCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        chartPanel.setChart(chart);
        this.revalidate();
        this.repaint();
    }

    private void formatirajComboBox(JComboBox cb){
        cb.addItem("PON");
        cb.addItem("UTO");
        cb.addItem("SRE");
        cb.addItem("ČET");
        cb.addItem("PET");
    }

    public JComboBox<String> getComboBoxDani() {
        return comboBoxDani;
    }

    public void setComboBoxDani(JComboBox<String> comboBoxDani) {
        this.comboBoxDani = comboBoxDani;
    }

    private IntervalCategoryDataset createDataset() {
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries series = new TaskSeries("Time Used");

        // ubacujem ucionice za y osu
        List<Task> ucionice = new ArrayList<>();
        List<String> ucioniceString = new ArrayList<>();

        // Assuming classrooms are stored in Cuvac.getInstance().getRaspored().getBozePomozi().get(6).keySet()
        for (String ucionica : Cuvac.getInstance().getRaspored().getBozePomozi().get(6).keySet()) {
            Task classroomTask = new Task(ucionica, date(2023, 0, 1, "08:00"), date(2023, 0, 1, "22:00"));
            ucionice.add(classroomTask);
            ucioniceString.add(ucionica);
        }

        List<Dogadjaj> dogadjajs = Cuvac.getInstance().getRaspored().vratiFiltrirano(prviUslov);
        for (Dogadjaj dogadjaj : dogadjajs) {
            for (Task task : ucionice) {
                if (dogadjaj.getStavkeDogadjaja().get(6).equals(task.getDescription().toString())) {
                    String vremena[] = dogadjaj.getStavkeDogadjaja().get(5).split("-");
                    Task subTask = new Task(dogadjaj.getStavkeDogadjaja().get(0), date(2023, 0, 1, vremena[0]), date(2023, 0, 1, vremena[1]));
                    task.addSubtask(subTask);
                }
            }
        }

        for (Task task : ucionice) {
            if (task.getSubtaskCount() > 0) { // Check if the task has subtasks
                series.add(task);
            }
        }

        GanttRenderer renderer = new CustomGanttRenderer();
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        DateAxis dateAxis = new DateAxis("Time");
        dateAxis.setDateFormatOverride(new SimpleDateFormat("hh-mm"));
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.HOUR, 1));
        plot.setRangeAxis(dateAxis);

        dataset.add(series);

        return dataset;
    }

    private JFreeChart createChart(IntervalCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createGanttChart(
                "Schedule",  // chart title
                "Classroom",                 // domain axis label
                "Time",                 // range axis label
                dataset,                // data
                true,                   // include legend
                true,                   // tooltips
                true                   // urls
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        DateAxis dateAxis = (DateAxis) plot.getRangeAxis();
        dateAxis.setDateFormatOverride(new SimpleDateFormat("hh-mm"));
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.HOUR, 1));

        GanttRenderer renderer = new CustomGanttRenderer();
        plot.setRenderer(renderer);

        return chart;
    }

    //17:15-19
    private Date date(int year, int month, int day, String inputTime) {
        Calendar calendar = Calendar.getInstance();
        String komadi[] = inputTime.split(":");
        if(komadi.length > 1) {
            int sati = Integer.parseInt(komadi[0]);
            int minuti = Integer.parseInt(komadi[1]);
            calendar.set(year, month, day, sati, minuti);
        }else{
            int sati = Integer.parseInt(komadi[0]);
            int minuti = 0;
            calendar.set(year,month,day,sati,minuti);
        }
        return calendar.getTime();
    }
    // Custom GanttRenderer to set different colors for each subtask
    private static class CustomGanttRenderer extends GanttRenderer {
        @Override
        public Paint getItemPaint(int row, int column) {
            IntervalCategoryDataset dataset = (IntervalCategoryDataset) getPlot().getDataset();
            if (dataset instanceof TaskSeriesCollection) {
                TaskSeriesCollection taskDataset = (TaskSeriesCollection) dataset;
                TaskSeries series = taskDataset.getSeries(row);
                Task task = series.get(column);
                //za boje bi ideja bila ovakva, dalje se ostavlja na interpretaciji korisnika
                if (task.getDescription() != null) {
                    switch (task.getDescription()) {
                        case "Skript jezici":
                            return Color.red;
                        case "Softverske komponente":
                            return Color.blue;
                        case "Programski prevodioci":
                            return Color.MAGENTA;

                        // Add more cases for other subtasks as needed
                    }
                }
            }

            // Default color if not matched
            return super.getItemPaint(row, column);
        }
    }





    public SlobodnostController getSlobodnostController() {
        return slobodnostController;
    }

    public void setSlobodnostController(SlobodnostController slobodnostController) {
        this.slobodnostController = slobodnostController;
    }

    public String getPrviUslov() {
        return prviUslov;
    }

    public void setPrviUslov(String prviUslov) {
        this.prviUslov = prviUslov;
    }
}
