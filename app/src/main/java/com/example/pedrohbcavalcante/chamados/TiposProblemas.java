package com.example.pedrohbcavalcante.chamados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class TiposProblemas extends DialogFragment implements DialogInterface.OnClickListener {

    private String tipoSelecionado;
    private OnTextListener listener;

    public static void show(FragmentManager fragmentManager, OnTextListener listener){
        TiposProblemas dialogProblemas = new TiposProblemas();
        dialogProblemas.listener = listener;
        dialogProblemas.show(fragmentManager, "selecionarTipoProblemaFragment");

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder selecionarTipoProblema = new AlertDialog.Builder(getActivity());
        selecionarTipoProblema.setTitle("Selecione");
        selecionarTipoProblema.setSingleChoiceItems(R.array.tipos_problemas, 0, this);

        return selecionarTipoProblema.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String[] tipos_problemas = getActivity().getResources().getStringArray(R.array.tipos_problemas);
        tipoSelecionado = tipos_problemas[which];
        listener.onSelectTipoSolicitacao(tipoSelecionado);
        dismiss();
    }


    public interface OnTextListener{
        void onSelectTipoSolicitacao(String text);
    }
}
