package com.example.pedrohbcavalcante.chamados;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;

public class AboutDialog extends DialogFragment implements DialogInterface.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder aboutDialogBuilder = new AlertDialog.Builder(getActivity());
        aboutDialogBuilder.setTitle("Sobre");
        aboutDialogBuilder.setMessage("App desenvolvido por Pedro Henrique Bezerra Cavalcante como forma a obter a nota da terceira unidade da " +
                "disciplina DIM0524 - DESENVOLVIMENTO DE SISTEMAS PARA DISPOSITIVOS MÓVEIS");
        aboutDialogBuilder.setPositiveButton("Ok", this);
        return aboutDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE){
            dismiss();
        }

    }
}
