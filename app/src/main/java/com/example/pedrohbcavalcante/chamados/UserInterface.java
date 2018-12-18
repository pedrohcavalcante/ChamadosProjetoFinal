package com.example.pedrohbcavalcante.chamados;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserInterface {

    @GET("users/email/{email}")
    Call<Usuario> recuperaUsuario(@Path("email") String email);
}
