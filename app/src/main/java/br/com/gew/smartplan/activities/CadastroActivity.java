package br.com.gew.smartplan.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.client.RetrofitClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtUser;
    private EditText txtSenha;
    private EditText txtConfirmar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastrar");

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtUser = findViewById(R.id.txtUser);
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmar = findViewById(R.id.txtConfirmar);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v -> {
            ProfessorClient pc = RetrofitClient.getRetrofit().create(ProfessorClient.class);
            Professor professor = new Professor(txtNome.getText().toString(), txtEmail.getText().toString(),
                    txtUser.getText().toString(), txtSenha.getText().toString());
            Call c = pc.cadastrar(professor);
            c.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    finish();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Utils.showMessage(getApplicationContext(), "Alguma coisa deu errado", 0);
                    Log.d("Failure: ", t.getMessage());
                }
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

