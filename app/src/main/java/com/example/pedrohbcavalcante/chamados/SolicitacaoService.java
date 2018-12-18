package com.example.pedrohbcavalcante.chamados;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SolicitacaoService {

    @POST("solicitacoes")
    Call<Solicitacao> enviarSolicitacao(@Body Solicitacao solicitacao);

    @GET("solicitacoes")
    Call<List<Solicitacao>> recuperarSolicitacoes();

}
