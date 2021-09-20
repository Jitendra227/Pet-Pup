package com.jitendra.petpup.model;

import com.jitendra.petpup.model.data.PupItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCallInterface {

    @GET("all")
    Call<PupItems> getBreedNames();

    @GET("{dogname}/images")
    Call<PupItems> getBreedImages(@Path("dogname") String breed);
}
