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

import org.w3c.dom.Text;

public class CadastroSolicitacaoActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView tipoProblemaSelec;
    TextView tipoParaSelec;
    TextView tipoServicoSelec;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_solicitacao);
        tipoProblemaSelec = findViewById(R.id.tipoProbSelecionado);
        tipoParaSelec = findViewById(R.id.paraSelecionado);
        tipoServicoSelec = findViewById(R.id.servicoSelecionado);
        mImageView = findViewById(R.id.image_foto_capturada);
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
        ParaLista.show(getSupportFragmentManager(), new ParaLista.onParaListener() {
            @Override
            public void onSelectPara(String text) {
                tipoParaSelec.setText(text);
            }
        });

    }

    public void selecionarServico(View view) {

        ServicosLista.show(getSupportFragmentManager(), new ServicosLista.onServicoListener() {
            @Override
            public void onServicoSelect(String text) {
                tipoServicoSelec.setText(text);
            }
        });
    }

    public void tirarFoto(View view) {
        dispatchTakePictureIntent();
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


    public void enviarChamado(View view) {

    }
}
