package Model;

import com.example.solceller.Entry;
import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author NMP
 */

public class Graph
{
    private XYChart<String, Number> chart;

    /**
     * Constructor that initializes the attributes and changes chart type depending on enum type
     * @param title The title for chart
     * @param titleX Title for x-axis
     * @param titleY Title for y-axis
     * @param type Enum representing chart type
     */
    public Graph(String title, String titleX, String titleY, Type type)
    {
        NumberAxis y_AXIS = new NumberAxis();
        CategoryAxis x_AXIS = new CategoryAxis();
        x_AXIS.setLabel(titleX);
        y_AXIS.setLabel(titleY);
        switch (type)
        {
            case CURVE_CHART -> this.chart = new LineChart<>(x_AXIS, y_AXIS);
            case BAR_CHART -> this.chart = new BarChart<>(x_AXIS, y_AXIS);
            case AREA_CHART -> this.chart = new AreaChart<>(x_AXIS, y_AXIS);
        }
        this.chart.setTitle(title);
        this.chart.setScaleX(1.2);
        this.chart.setScaleY(1.2);
        this.chart.setLayoutX(300);
        this.chart.setLayoutY(50);
    }

    /**
     * Creates a XYChart series for production per hour and adds it to the XYChart
     * @param name The name of the series
     * @param list An arrayList containing the values for the series
     */
    public void CreateSeriesHour(String name, ArrayList<Entry> list)
    {
        XYChart.Series<String, Number> seriesName = new XYChart.Series<>();
        seriesName.setName(name);

        for ( Entry value : list )
        {
            try
            {
                seriesName.getData().add(new XYChart.Data<>(value.getTime(), value.getOnline() + value.getOffline()));
            }
            catch (Exception e)
            {
                System.out.println("Null reference");
            }
        }
        this.chart.getData().add(seriesName);
    }

    /**
     * Creates a XYChart series for production per day and adds it to the XYChart
     * @param name The name of the series
     * @param productionDay An TreeMap containing the values for the series
     */
    public void CreateSeries(String name, TreeMap<String, Integer> productionDay)
    {
        XYChart.Series<String, Number> seriesName = new XYChart.Series<>();
        seriesName.setName(name);

        for (Map.Entry<String, Integer> value : productionDay.entrySet() )
        {
            try
            {
                seriesName.getData().add(new XYChart.Data<>(value.getKey(), value.getValue()));
                System.out.println("Key: " + value.getKey() + " - Value: " + value.getValue());
            }
            catch (Exception e)
            {
                System.out.println("Null reference");
            }
        }

        this.chart.getData().add(seriesName);
    }
    public XYChart<String, Number> getChart()
    {
        return chart;
    }
}