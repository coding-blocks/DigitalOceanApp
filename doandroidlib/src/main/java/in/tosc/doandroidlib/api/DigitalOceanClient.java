package in.tosc.doandroidlib.api;

import java.util.List;

import in.tosc.doandroidlib.common.ActionType;
import in.tosc.doandroidlib.objects.Account;
import in.tosc.doandroidlib.objects.Action;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Image;
import in.tosc.doandroidlib.objects.Regions;
import in.tosc.doandroidlib.objects.Size;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<List<Image>> getImages(
            @Query("page") int pageNo,
            @Query("per_page") int dropsPerPage,
            @Query("type") String type
    );

    @GET("sizes")
    Call<List<Size>> getSizes();

    @FormUrlEncoded
    @POST("droplets/{id}/actions")
    Call<Action> performAction(
            @Path("id") Integer dropletId,
            @Field("type") ActionType actionType,
            @Field("name") String name              // for rename or snapshot
            );

    //For getting regions
    @GET("regions")
    Call<Regions> getRegions();


}
