package com.example.pedrohbcavalcante.chamados;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroSolicitacaoActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView tipoProblemaSelec;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_solicitacao);
        tipoProblemaSelec = findViewById(R.id.tipoProbSelecionado);
    }

    public void selecionarTipoProblema(View view) {
        TiposProblemas.show(getSupportFragmentManager(), new TiposProblemas.OnTextListener() {
            @Override
            public void onSelectTipoSolicitacao(String text) {
                tipoProblemaSelec.setText(text);
            }
        });
    }

    public void selecionarPara(View view) {

    }

    public void selecionarServico(View view) {

    }

    private void dispatchTakePictureIntent(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
}
