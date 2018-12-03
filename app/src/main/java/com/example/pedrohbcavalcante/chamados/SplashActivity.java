package com.example.pedrohbcavalcante.chamados;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 3000);
    }

    public void mostrarLogin(){
        //TODO colocar a intent de destino
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
