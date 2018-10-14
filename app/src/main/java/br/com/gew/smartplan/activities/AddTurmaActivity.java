package br.com.gew.smartplan.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.tasks.AddTurmaTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTurmaActivity extends AppCompatActivity {

    private Long id;

    @BindView(R.id.spinner_cores)
    Spinner cores;

    @BindView(R.id.txt_turma_nome)
    EditText turmaNome;

    @BindView(R.id.txt_turma_sala)
    EditText turmaSala;

    @BindView(R.id.txt_turma_descricao)
    EditText turmaDescricao;

    @BindView(R.id.insert_turma)
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_turma);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Turma");

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);

        ButterKnife.bind(this);
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

    @OnClick(R.id.insert_turma)
    public void insertTurma() {

        boolean resultado = false;

        try {
            resultado = new AddTurmaTask().execute("0", turmaSala.getText().toString(), turmaNome.getText().toString(), turmaDescricao.getText().toString(), Long.toString(id)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(!resultado) showMessage("Essa nao!");
        else {
            showMessage("Foi! Pode relaxar, Gabriel.");
            finish();
        }
    }

    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }
}
