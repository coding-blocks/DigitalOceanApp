package in.tosc.doandroidlib.api;

import in.tosc.doandroidlib.common.ActionType;
import in.tosc.doandroidlib.objects.AccountInfo;
import in.tosc.doandroidlib.objects.Action;
import in.tosc.doandroidlib.objects.Droplet;
import in.tosc.doandroidlib.objects.Droplets;
import in.tosc.doandroidlib.objects.Images;
import in.tosc.doandroidlib.objects.Regions;
import in.tosc.doandroidlib.objects.Sizes;
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
    Call<AccountInfo> getAccount();

    @GET("droplets")
    Call<Droplets> getDroplets(
            @Query("page") int pageNo,
            @Query("per_page") int dropsPerPage
    );

    @GET("images")
    Call<Images> getImages(
            @Query("page") int pageNo,
            @Query("per_page") int dropsPerPage,
            @Query("type") String type
    );

    @GET("sizes")
    Call<Sizes> getSizes();

    @FormUrlEncoded
    @POST("droplets/{id}/actions")
    Call<Action> performAction(
            @Path("id") Integer dropletId,
            @Field("type") ActionType actionType,
            @Field("name") String name             // for rename or snapshot
    );

    //For getting regions
    @GET("regions")
    Call<Regions> getRegions();


    @FormUrlEncoded
    @POST("droplets")
    Call<Droplet> createDroplet(
            @Field("name") String name,
            @Field("region") String region,
            @Field("size") String size,
            @Field("image") String image,
            @Field("backups") Boolean backups,
            @Field("ipv6") Boolean ipv6,
            @Field("private_networking") Boolean private_networking

    );


}
