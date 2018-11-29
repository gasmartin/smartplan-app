package br.com.gew.smartplan.activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.TurmaClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;
import br.com.gew.smartplan.model.Turma;
import br.com.gew.smartplan.model.Usuario;

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

        SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.shared), MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);


        turmaNome = findViewById(R.id.txt_turma_nome);
        turmaSala = findViewById(R.id.txt_turma_sala);

        insert = findViewById(R.id.insert_turma);
        insert.setOnClickListener(v -> {
            Turma turma = null;
            try {
                turma = new AddTurma().execute(Long.toString(id), turmaSala.getText().toString(), turmaNome.getText().toString()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(turma != null){
                Utils.showMessage(getApplicationContext(), "Inserido com sucesso!", 0);
                finish();
            }
            else Utils.showMessage(getApplicationContext(), "Algum erro!", 0);
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

    private class AddTurma extends AsyncTask<String, Void, Turma>{
        @Override
        protected Turma doInBackground(String... strings) {
            return new TurmaClient().insert(strings[0], new Turma(Integer.parseInt(strings[1]), strings[2]));
        }
        @Override
        protected void onPostExecute(Turma t) {
            super.onPostExecute(t);
        }
    }
}
