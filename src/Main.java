import DrawAndLog.Chart;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Chart chart = new Chart(
                "Visualization",
                "ConcurrentCollections vs Collections.synchronized");

        chart.pack();
        chart.setVisible(true);
        chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


    }

}




