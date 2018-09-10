package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.adapters.TurmasAdapter;
import br.com.gew.smartplan.model.Turma;
import br.com.gew.smartplan.tasks.TurmasListTask;

public class TabbedActivity extends AppCompatActivity {

    private Button sair;
    private RecyclerView rv_objetos;

    final SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_turma:
                    return true;
                case R.id.navigation_planejamento:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Long id = preferences.getLong("professor_id", new Long(0);
        //List<Turma> turmaList = new TurmasListTask().execute(id).get();

        rv_objetos = findViewById(R.id.rv_objetos);
        //rv_objetos.setAdapter(new TurmasAdapter(turmaList, this));

        //RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
        //        LinearLayoutManager.VERTICAL, false));
        //rv_objetos.setLayoutManager(layout);

        sair = findViewById(R.id.sair);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent LoginActivity = new Intent(TabbedActivity.this, MainActivity.class);
                startActivity(LoginActivity);
            }
        });
    }

    @Override
    public void onBackPressed() { this.moveTaskToBack(true); }

}
