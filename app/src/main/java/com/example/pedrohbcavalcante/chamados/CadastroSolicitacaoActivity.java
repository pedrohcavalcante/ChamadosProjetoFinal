package com.example.pedrohbcavalcante.chamados;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

public class CadastroSolicitacaoActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView tipoProblemaSelec;
    TextView tipoParaSelec;
    TextView tipoServicoSelec;
    private ImageView mImageView;

    private FirebaseStorage firebaseStorage;

    private StorageReference storageReference;
    private Uri filePath;
    String url_referencia = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_solicitacao);

        tipoProblemaSelec = findViewById(R.id.tipoProbSelecionado);

        tipoParaSelec = findViewById(R.id.paraSelecionado);

        tipoServicoSelec = findViewById(R.id.servicoSelecionado);

        mImageView = findViewById(R.id.image_foto_capturada);

        firebaseStorage = FirebaseStorage.getInstance();

        permissao();

    }

    private void permissao() {

        String permissoes[] = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        Permissao.permissao(this,1,permissoes);
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
        Log.i("TakePicture", "criou intent");

        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.i("TakePicture", "criou file diretorio");

        String nomeImagem = diretorio.getPath() + "/" + "foto" + System.currentTimeMillis() + ".jpg";
        Log.i("TakePicture", "criou string nomeImagem " + nomeImagem);

        File file = new File(nomeImagem);
        Log.i("TakePicture", "criou file a partir d enome imagem");

        if (file == null){
            Log.i("TakePicture", "file é null");
        }

        String autorizacao = "com.example.pedrohbcavalcante.chamados";
        Log.i("TakePicture", "definiu string de autorizacao");

        filePath = FileProvider.getUriForFile(this, autorizacao, file);
        Log.i("TakePicture", "setou filePath pelo file provider");

        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, filePath);
        Log.i("TakePicture", "take picture put extra");

        startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        Log.i("TakePicture", "start activity para foto");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            if (filePath != null) {
                Picasso.get().load(filePath).into(mImageView);
            }

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);

            //filePath = data.getData();

            //Bitmap file = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

        }
    }


    public void enviarChamado(View view) {

        storageReference = firebaseStorage.getReference()
                .child("uploads")
                .child("imagens");

        final StorageReference nome_imagem = storageReference.child("imagem_upload" + System.currentTimeMillis() + ".jpg");

        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        UploadTask uploadTask = nome_imagem.putBytes(bytes.toByteArray());
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.i("TakePicture", nome_imagem.getDownloadUrl().toString());
                    Log.i("TakePicture", nome_imagem.getName());

                    Toast.makeText(CadastroSolicitacaoActivity.this, "Sucesso no upload em " + url_referencia, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(CadastroSolicitacaoActivity.this, "Falha no upload", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults){
            if (result == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Aceite as permissões do app", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
        }
    }
}
