package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.ProfessorClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Professor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        Long verify = preferences.getLong("professor_id", 0);

        if (verify != 0) {
            Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeActivity);
        }

        txtUser = findViewById(R.id.txtUser);
        txtSenha = findViewById(R.id.txtSenha);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ProfessorClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            ProfessorClient pc = retrofit.create(ProfessorClient.class);
            Call c = pc.executarLogin(txtUser.getText().toString(), txtSenha.getText().toString());
            c.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Professor p = (Professor) response.body();

                    SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putLong("id", p.getId());
                    editor.commit();

                    Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeActivity);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Utils.showMessage(getApplicationContext(), "Alguma coisa deu errado", 0);
                    Log.d("Failure: ", t.getMessage());
                }
            });
        });

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v -> {
            Intent telaCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(telaCadastrar);
        });
    }
}
