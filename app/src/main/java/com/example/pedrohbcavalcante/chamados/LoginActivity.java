package com.example.pedrohbcavalcante.chamados;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    final String BASE_URL = "http://10.7.8.141:8080/api/";
    private Retrofit conexaoRetrofit;



    FirebaseAuth autenticacao;
    EditText usuario;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        autenticacao = FirebaseAuth.getInstance();

        usuario = findViewById(R.id.input_usuario);
        senha = findViewById(R.id.input_senha);

        conexaoRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public void fazerLogin(View view) {

        if (usuario.getText().toString().equals("") || senha.getText().toString().equals("")) {

            final Snackbar usuarioVazio = Snackbar.make(findViewById(R.id.coordinator), "Usuário/Senha não pode ser em branco", Snackbar.LENGTH_SHORT);
            usuarioVazio.setAction("Fechar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarioVazio.dismiss();
                }
            });

            usuarioVazio.show();

        } else {

            autenticacao.signInWithEmailAndPassword(usuario.getText().toString().trim(),
                    senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final Intent loginSucesso = new Intent(LoginActivity.this, MainActivity.class);
                        final Bundle usuarioBundle = new Bundle();
                        Toast.makeText(LoginActivity.this, "opa, entrou no task.isSuccessful", Toast.LENGTH_SHORT).show();

                        UserInterface userLogin = conexaoRetrofit.create(UserInterface.class);

                        Call<Usuario> call = userLogin.recuperaUsuario(usuario.getText().toString());

                        call.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                if (response.isSuccessful()){
                                    Log.i("loginMethod", "Deu certo " + response.body().toString());
                                    Usuario usuario = response.body();
                                    loginSucesso.putExtra("usuario", usuario);
                                    startActivity(loginSucesso);
                                } else {
                                    Log.i("loginMethod", "Não deu certo");
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Log.i("loginMethod", t.getMessage());
                            }
                        });


                    } else {

                        final Snackbar naoFezLogin = Snackbar.make(findViewById(R.id.coordinator), "Não foi possível realizar o login", Snackbar.LENGTH_SHORT);
                        naoFezLogin.setAction("Fechar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                naoFezLogin.dismiss();
                            }
                        });

                        naoFezLogin.show();

                    }
                }
            });
        }


    }
}
