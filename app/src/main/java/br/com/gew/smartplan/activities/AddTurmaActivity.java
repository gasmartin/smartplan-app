package br.com.gew.smartplan.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.RetrofitClient;
import br.com.gew.smartplan.client.TurmaClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Turma;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddTurmaActivity extends AppCompatActivity {

    private Long id;

    private EditText turmaNome;
    private EditText turmaSala;

    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_turma);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Turma");

        SharedPreferences sf = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        id = sf.getLong("professor_id", 0);

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);


        turmaNome = findViewById(R.id.txt_turma_nome);
        turmaSala = findViewById(R.id.txt_turma_sala);

        insert = findViewById(R.id.insert_turma);
        insert.setOnClickListener(v -> {
            TurmaClient tc = RetrofitClient.getRetrofit().create(TurmaClient.class);
            Turma t = new Turma(Integer.parseInt(turmaSala.getText().toString()), turmaNome.getText().toString());
            Call c = tc.insertTurma(id, t);
            c.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    finish();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Utils.showMessage(getApplicationContext(), "Não deu certo!", 0);
                    Log.d("AddTurmaActivity: ", t.getMessage());
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
