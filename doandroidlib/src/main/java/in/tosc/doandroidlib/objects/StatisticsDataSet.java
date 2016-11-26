package in.tosc.doandroidlib.objects;

import java.util.List;

/**
 * Created by championswimmer on 27/11/16.
 */

public class StatisticsDataSet {

    private String name;

    private String unit;

    private List<StatisticsData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<StatisticsData> getData() {
        return data;
    }

    public void setData(List<StatisticsData> data) {
        this.data = data;
    }
}
