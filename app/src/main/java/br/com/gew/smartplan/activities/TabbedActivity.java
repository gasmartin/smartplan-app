package br.com.gew.smartplan.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.gew.smartplan.R;

public class TabbedActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button sair;
    private TextView nome;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_turma:
                    mTextMessage.setText(R.string.title_turma);
                    return true;
                case R.id.navigation_planejamento:
                    mTextMessage.setText(R.string.title_planejamento);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        sair = findViewById(R.id.sair);
        nome = findViewById(R.id.nome);

        final SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        nome.setText(preferences.getString("professor_name", "Não achado"));

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
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

}
