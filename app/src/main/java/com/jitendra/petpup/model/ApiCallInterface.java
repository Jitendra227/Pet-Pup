package com.jitendra.petpup.model;

import com.jitendra.petpup.model.data.PupItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCallInterface {

    @GET("/all")
    Call<List<PupItems>> getPupList();
}
