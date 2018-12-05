package br.com.gew.smartplan.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Evento;
import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoActivity extends AppCompatActivity {

    private Planejamento planejamento;
    private List<Evento> eventos;

    private CalendarView calendar;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento);

        calendar = findViewById(R.id.calendar);

        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        id = preferences.getLong("professor_id", 0);

        planejamento = (Planejamento) getIntent().getExtras().getSerializable("planejamento");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(planejamento.getNome());

        if(eventos != null) Log.d("Testando:", "Não tá vazio");

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(Utils.stringToDate(planejamento.getDataInicio()));
        max.setTime(Utils.stringToDate(planejamento.getDataFinal()));
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
