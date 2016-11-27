package in.tosc.doandroidlib.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by championswimmer on 27/11/16.
 */

public class Statistics {

    @SerializedName("statistics")
    private List<StatisticsDataSet> statistics;

    public List<StatisticsDataSet> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsDataSet> statistics) {
        this.statistics = statistics;
    }
}
