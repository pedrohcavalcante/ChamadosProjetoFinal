package com.example.pedrohbcavalcante.chamados;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {


    public static boolean permissao(Activity activity, int requestCode, String[] permissoes) {

        List<String> list = new ArrayList<String>();


        for (String permissao : permissoes) {

            boolean ok = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;


            if (!ok) {

                list.add(permissao);
            }
        }


        if (list.isEmpty()) {
            return true;
        }


        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);


        ActivityCompat.requestPermissions(activity, newPermissions, requestCode);
        return false;
    }


}