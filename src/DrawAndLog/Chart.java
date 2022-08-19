package DrawAndLog;

import Maps.MapReadWriteTesting;
import Maps.WorkWithConcurrentMap;
import Maps.WorkWithSynchronizedMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Chart extends JFrame {
    private JFreeChart lineChart;

    public Chart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        lineChart = ChartFactory.createLineChart(
                chartTitle,
                "MapSize", "Time, ms",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1080, 700));
        setContentPane(chartPanel);

    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        MapReadWriteTesting synchronizedHasMap = new WorkWithSynchronizedMap("SynchronizedMap",
                Collections.synchronizedMap(new HashMap<>()));

        MapReadWriteTesting concurrentHasMap = new WorkWithConcurrentMap("ConcurrentHasMap",
                new ConcurrentHashMap<>());

        for (int a = 1; a <= 1_000_000; a *= 10) {
            dataset.addValue(concurrentHasMap.multiThreadingPut(a), "ConcurrentHasMapPut", String.valueOf(a));
            dataset.addValue(concurrentHasMap.multiThreadingRead(a), "ConcurrentHasMapRead", String.valueOf(a));
            dataset.addValue(synchronizedHasMap.multiThreadingPut(a), "SynchronizedHasMapPut", String.valueOf(a));
            dataset.addValue(synchronizedHasMap.multiThreadingRead(a), "SynchronizedHasMapRead", String.valueOf(a));
        }


        return dataset;
    }

}


