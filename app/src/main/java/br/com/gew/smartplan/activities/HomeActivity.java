package br.com.gew.smartplan.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.gew.smartplan.R;
import br.com.gew.smartplan.fragments.PerfilFragment;
import br.com.gew.smartplan.fragments.PlanejamentoFragment;
import br.com.gew.smartplan.fragments.TurmaFragment;

public class HomeActivity extends AppCompatActivity {

    private Fragment selectedFragment = null;

    FloatingActionButton btn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_turmas:
                    selectedFragment = new TurmaFragment();
                    /*btn.setOnClickListener(v -> {
                        Intent addTurma = new Intent(HomeActivity.this, AddTurmaActivity.class);
                        startActivity(addTurma);
                    });*/
                    break;
                case R.id.navigation_planejamentos:
                    selectedFragment = new PlanejamentoFragment();
                    /*btn.setOnClickListener(v -> {
                        Intent addPlanejamento = new Intent(HomeActivity.this, AddPlanejamentoActivity.class);
                        startActivity(addPlanejamento);
                    });*/
                    break;
                case R.id.navigation_perfil:
                    selectedFragment = new PerfilFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        selectedFragment = new TurmaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //btn = findViewById(R.id.add_button);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTurma = new Intent(HomeActivity.this, AddTurmaActivity.class);
                startActivity(addTurma);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(selectedFragment instanceof  TurmaFragment){
            selectedFragment = new TurmaFragment();
        }
        else {
            selectedFragment = new PlanejamentoFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Deseja realmente sair?").setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor prefsEditor = getSharedPreferences("UserPreferences", MODE_PRIVATE).edit();
                        prefsEditor.clear();
                        prefsEditor.commit();
                        Intent mainActivity = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                });

                builder.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
