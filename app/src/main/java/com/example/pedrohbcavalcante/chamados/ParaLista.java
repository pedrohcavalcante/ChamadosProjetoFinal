package com.example.pedrohbcavalcante.chamados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class ParaLista extends DialogFragment implements DialogInterface.OnClickListener {

    private onParaListener onParaListener;
    private String paraSelecionado;

    public static void show(FragmentManager fragmentManager, onParaListener onParaListener){
        ParaLista paraLista = new ParaLista();
        paraLista.onParaListener = onParaListener;
        paraLista.show(fragmentManager, "selecionarParaLista");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder selecionarPara = new AlertDialog.Builder(getActivity());
        selecionarPara.setTitle("Selecione");
        selecionarPara.setSingleChoiceItems(R.array.para, 0, this);

        return selecionarPara.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String[] selecionarPara = getActivity().getResources().getStringArray(R.array.para);
        paraSelecionado = selecionarPara[which];
        onParaListener.onSelectPara(paraSelecionado);
        dismiss();
    }

    public interface onParaListener{
        void onSelectPara(String text);
    }
}
