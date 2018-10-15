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
import br.com.gew.smartplan.tasks.AddPlanejamentoTask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPlanejamentoActivity extends AppCompatActivity {

    @BindView(R.id.txt_nome_planejamento)
    EditText txtNome;
    @BindView(R.id.txt_descricao_planejamento)
    EditText txtDescricao;
    @BindView(R.id.spinner_cores)
    Spinner spinner;
    @BindView(R.id.insert_planejamento)
    Button insert;

    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planejamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Planejamento");

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

    @OnClick(R.id.insert_planejamento)
    public void insertPlanejamento(){
        boolean resultado = false;

        try {
            resultado = new AddPlanejamentoTask().execute("0", txtNome.getText().toString(), txtDescricao.getText().toString(), Long.toString(id)).get();
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
