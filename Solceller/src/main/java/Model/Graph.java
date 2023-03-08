package Model;

import com.example.solceller.Entry;
import javafx.scene.chart.*;

import java.util.ArrayList;

public class Graph
{
    private XYChart<String, Number> chart;
    private final CategoryAxis X_AXIS = new CategoryAxis();
    private final NumberAxis Y_AXIS = new NumberAxis();

    public Graph(String title, String titleX, String titleY, Type type)
    {
        switch (type)
        {
            case CURVE_CHART -> this.chart = new LineChart<>(X_AXIS, Y_AXIS);
            case BAR_CHART -> this.chart = new BarChart<>(X_AXIS, Y_AXIS);
            case AREA_CHART -> this.chart = new AreaChart<>(X_AXIS, Y_AXIS);
        }
        this.chart.setTitle(title);
    }

    public void CreateSeries(String name, ArrayList<Entry> list)
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

    public void setTitle(String titleX, String titleY)
    {
        X_AXIS.setLabel(titleX);
        Y_AXIS.setLabel(titleY);
    }

    public XYChart<String, Number> getChart()
    {
        return chart;
    }
}