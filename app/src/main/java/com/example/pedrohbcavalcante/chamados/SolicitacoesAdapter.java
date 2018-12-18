package com.example.pedrohbcavalcante.chamados;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SolicitacoesAdapter extends RecyclerView.Adapter<SolicitacoesAdapter.ViewHolder> {

    List<Solicitacao> myListSolicitacoes;

    private Usuario userLogged;
    private Retrofit retrofit;

    public void setUserLogged(Usuario userLogged) {
        this.userLogged = userLogged;
    }

    private Context context;

    public SolicitacoesAdapter(final Context context){
        this.context = context;

        myListSolicitacoes = new ArrayList<>();
        /*myListSolicitacoes.add(new Solicitacao( 2, 234, "Teste", "aprovado"));
        myListSolicitacoes.add(new Solicitacao( 2, 2345, "Teste 2", "aguardando"));*/

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.7.8.141:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SolicitacaoService service = retrofit.create(SolicitacaoService.class);
        Call<List<Solicitacao>> call = service.recuperarSolicitacoes();
        call.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if (response.isSuccessful()) {

                    myListSolicitacoes = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.solicitacao, viewGroup, false);
        /*retrofit = new Retrofit.Builder()
                .baseUrl("http://10.7.8.141:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.idSolicitacao.setText(String.valueOf(myListSolicitacoes.get(i).getTicket()));
        viewHolder.textoSolicitacao.setText(String.valueOf(myListSolicitacoes.get(i).getTitulo()));
        viewHolder.statusSolicitacao.setText(String.valueOf(myListSolicitacoes.get(i).getStatus()));
    }

    @Override
    public int getItemCount() {
        return myListSolicitacoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView idSolicitacao;
        public TextView textoSolicitacao;
        public TextView statusSolicitacao;
        public Button verSolicitacao;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idSolicitacao = itemView.findViewById(R.id.idSolicitacao);
            textoSolicitacao = itemView.findViewById(R.id.textoSolicitacao);
            statusSolicitacao = itemView.findViewById(R.id.statusSolicitacao);
            verSolicitacao = itemView.findViewById(R.id.btnVer);

            verSolicitacao.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Voce arrasou ", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
