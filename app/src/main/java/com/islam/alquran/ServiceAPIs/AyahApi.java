package com.islam.alquran.ServiceAPIs;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AyahApi {

    @GET("/v1/edition/format/audio")
    Call<ResponseBody> getReciters();

    @GET("/media/audio/ayah/{reciter}/{ayahNum}")
    Call<ResponseBody> getAyah(@Path("reciter") String reciter, @Path("ayahNum") String ayahNum);

}
