package br.com.gew.smartplan.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.client.PlanejamentoClient;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;

public class AddPlanejamentoActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtDescricao;
    private EditText dataInicio;
    private EditText dataFinal;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planejamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Planejamento");

        SharedPreferences sp = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        id = sp.getLong("professor_id", 0);
        Log.d("Print ai", Long.toString(id));

        txtNome = findViewById(R.id.txt_nome_planejamento);
        txtDescricao = findViewById(R.id.txt_descricao_planejamento);

        Button insert = findViewById(R.id.insert_planejamento);
        insert.setOnClickListener(v -> {
            Planejamento planejamento = null;
            try {
                planejamento = new AddPlanejamento().execute(txtNome.getText().toString(), txtDescricao.getText().toString(),
                        dataInicio.getText().toString(), dataFinal.getText().toString()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (planejamento != null){
                finish();
            }
            else{
                Utils.showMessage(getBaseContext(), "error", 0);
            }
        });

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        this.id = preferences.getLong("professor_id", 0);

        dataInicio = findViewById(R.id.data_inicio);
        dataInicio.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                final Calendar c = Calendar.getInstance();
                new DatePickerDialog(AddPlanejamentoActivity.this, (view, year, month, dayOfMonth) -> {
                    c.set(year, month, dayOfMonth);
                    dataInicio.setText(Utils.dateToString(c.getTime()));
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dataFinal = findViewById(R.id.data_final);
        dataFinal.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                final Calendar c = Calendar.getInstance();
                new DatePickerDialog(AddPlanejamentoActivity.this, (view, year, month, dayOfMonth) -> {
                    c.set(year, month, dayOfMonth);
                    dataFinal.setText(Utils.dateToString(c.getTime()));
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
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

    private class AddPlanejamento extends AsyncTask<String, Void, Planejamento>{
        @Override
        protected Planejamento doInBackground(String... strings) {
            return new PlanejamentoClient().insert(id, new Planejamento(strings[0], strings[1], strings[2], strings[3], null));
        }
        @Override
        protected void onPostExecute(Planejamento p) {
            super.onPostExecute(p);
        }
    }
}
