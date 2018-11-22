package com.example.mario.ahorramas.Remote;

import com.example.mario.ahorramas.Item2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @POST("/pls/apex/pruebas123/api/CABECERA")
    @FormUrlEncoded
    Call<Item2> nuevaLista(@Field("CORREO") String correo,
                           @Field("FECHALISTA") String fechalista,
                           @Field("NOMBRELISTA") String nombrelista);

    @PUT("/pls/apex/pruebas123/api/CABE/{IDCABECERALISTA}")

    Call<Item2> actualizaLista(@Header("id") Integer iD,  @Path("IDCABECERALISTA") Integer iDCABECERALISTA,
                          @Query("FECHALISTA") String fECHALISTA,
                          @Query("NOMBRELISTA") String nOMBRELISTA);

    @PUT("/pls/apex/pruebas123/api/CABE/{IDCABECERALISTA}")
    @FormUrlEncoded
    Call<Item2> actualizaListas(@Path("IDCABECERALISTA") int IDCABECERALISTA,
                                @Body Item2 item2);
}
