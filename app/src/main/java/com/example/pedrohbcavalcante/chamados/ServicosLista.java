package com.example.pedrohbcavalcante.chamados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class ServicosLista extends DialogFragment implements DialogInterface.OnClickListener {

    private onServicoListener onServicoListener;
    private String servicoSelecionado;

    public static void show(FragmentManager fragmentManager, onServicoListener listener){
        ServicosLista servicosLista = new ServicosLista();
        servicosLista.onServicoListener = listener;
        servicosLista.show(fragmentManager, "selecionarTipoServicoFragment");

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder selecionarServico = new AlertDialog.Builder(getActivity());
        selecionarServico.setTitle("Selecione");
        selecionarServico.setSingleChoiceItems(R.array.servicos_diretoria_ti, 0, this);

        return selecionarServico.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String[] servicos = getActivity().getResources().getStringArray(R.array.servicos_diretoria_ti);
        servicoSelecionado = servicos[which];
        onServicoListener.onServicoSelect(servicoSelecionado);
        dismiss();
    }

    public interface onServicoListener{
        void onServicoSelect(String text);
    }
}
