package com.example.mario.ahorramas;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductosService {
    @GET("pls/apex/pruebas123/api1/productos/")
    Call<Productos>getProductos();

    @GET("pls/apex/pruebas123/api/productos/{categoria}")
    Call<Productos>getProductosAbarrotes(@Path("categoria") String categoria);

    @GET("/pls/apex/pruebas123/api/CABECERA/{correo}")
    Call<Productos>getCorreo(@Path("correo") String correo);

    @Multipart
    @PUT("/pls/apex/pruebas123/api/CABECERA/")
        Call<Productos>updateLista(@Query("FECHALISTA") String FECHALISTA,
                                   @Query("NOMBRELISTA") String NOMBRELISTA,
                                   @Query("IDCABECERALISTA") int IDCABECERALISTA);

    @Multipart
    @PUT("/pls/apex/pruebas123/api/CABECERA/")
    void actualizaLista(@Part("FECHALISTA") String fechalista,
                            @Part("NOMBRELISTA") String nombrelista,
                            @Part("IDCABECERALISTA") int IDCABECERALISTA);

    /*@PUT("/pls/apex/pruebas123/api/CABECERA/")
    Observable<AppAllowTicketResponse> checkRealTimeTicketStatus(
            @Query("reg_id") String ticket_id,
            @Query("gate") int gate_id
    );*/
}
