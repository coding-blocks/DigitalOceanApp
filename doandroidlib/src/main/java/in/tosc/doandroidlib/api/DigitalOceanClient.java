package in.tosc.doandroidlib.api;

import java.util.List;

import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Droplet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by championswimmer on 26/11/16.
 */

public interface DigitalOceanClient {

    @GET("account")
    Call<Account> getAccount();

    @GET("droplets")
    Call<List<Droplet>> getDroplets(@Query("page") int pageNo, @Query("per_page") int dropsPerPage);

}
