package in.tosc.doandroidlib.api;

import in.tosc.doandroidlib.common.PeriodType;
import in.tosc.doandroidlib.common.StatisticsType;
import in.tosc.doandroidlib.objects.Statistics;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by championswimmer on 27/11/16.
 */

public interface DigitalOceanStatisticsClient {

    @GET("droplets/{id}/statistics/{type}")
    Call<Statistics> getStats (
            @Path("id") Integer dropletId,
            @Path("type") StatisticsType statsType,
            @Query("period")PeriodType period
            );
}
