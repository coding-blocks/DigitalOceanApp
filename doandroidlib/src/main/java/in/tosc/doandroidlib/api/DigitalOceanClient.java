package in.tosc.doandroidlib.api;

import java.util.List;

import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Images;
import in.tosc.doandroidlib.objects.Size;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by championswimmer on 26/11/16.
 */

public interface DigitalOceanClient {

    @GET("account")
    Call<Account> getAccount();

    @GET("droplets")
    Call<List<Droplet>> getDroplets(
            @Query("page") int pageNo,
            @Query("per_page") int dropsPerPage
    );

    @GET("images")
    Call<List<Images>> getImages(
            @Query("page") int pageNo,
            @Query("per_page") int dropsPerPage,
            @Query("type") String type
    );

    @GET("sizes")
    Call<List<Size>> getSizes();

}
