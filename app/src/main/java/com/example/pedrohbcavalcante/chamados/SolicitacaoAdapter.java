package com.example.pedrohbcavalcante.chamados;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoAdapter extends RecyclerView.Adapter<SolicitacaoAdapter.SolicitacaoHolder> {

    private List<Solicitacao> solicitacoesList = new ArrayList<>();
    private Context context;
    public SolicitacaoAdapter(Context context){
        this.context = context;
        //TODO COLOCAR PARA ADICIONAR NA LISTA AS SOLICITAÇÔES CONSUMIDAS DA API
    }

    @NonNull
    @Override
    public SolicitacaoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitacaoHolder solicitacaoHolder, int i) {
        Solicitacao solicitacao = solicitacoesList.get(i);
        

    }

    @Override
    public int getItemCount() {
        return solicitacoesList.size();
    }

    public class SolicitacaoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public SolicitacaoHolder(View solicitacaoView){
            super(solicitacaoView);
            // TODO
        }
        @Override
        public void onClick(View v) {

        }
    }
}
