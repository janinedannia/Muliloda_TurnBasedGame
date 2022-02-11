package com.example.muliloda_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtHeroHP, txtEnemyHP, txtHeroDMG, txtEnemyDMG, txtCombatLog;
    Button btnSlash;
    ImageView mrdarcy = findViewById(R.id.mrDarcy);
    ImageView mrwickham = findViewById(R.id.mrWickham);

    String heroName = "Mr. Darcy";
    int heroHP = 2000;
    int heroMinDamage = 200;
    int heroMaxDamage = 300;

    String enemyName = "Mr. Wickham";
    int enemyHP = 2500;
    int enemyMinDamage = 100;
    int enemyMaxDamage = 150;

    int turnNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCombatLog = findViewById(R.id.txtCombatLog);
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtEnemyHP= findViewById(R.id.txtEnemyHP);
        txtHeroDMG = findViewById(R.id.txtEnemyDMG);
        btnSlash = findViewById(R.id.btnSlash);



        txtHeroHP.setText(String.valueOf(heroHP));
        txtHeroDMG.setText(String.valueOf(txtHeroDMG));
        txtHeroDMG.setText("Damage per turn:" + heroMinDamage+ "-"+ heroMaxDamage);

        txtEnemyHP.setText(String.valueOf(enemyHP));
        txtEnemyDMG.setText(String.valueOf(txtEnemyDMG));
        txtEnemyDMG.setText("Damage per turn "+ enemyMinDamage+ " - "+ enemyMaxDamage);


        btnSlash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        Random randomizer = new Random();
        int txtHeroDMG = randomizer.nextInt((heroMaxDamage - heroMinDamage) + heroMaxDamage );
        int txtEnemyDMG = randomizer.nextInt((enemyMaxDamage - enemyMinDamage) + enemyMaxDamage );



        switch(v.getId()) {
            case R.id.btnSlash:
                if (turnNumber%2 == 1) {
                    mrdarcy.setVisibility(View.VISIBLE);
                    mrwickham.setVisibility(View.VISIBLE);
                    enemyHP = Math.max(0,enemyHP - txtHeroDMG);
                    txtCombatLog.setText("Mr Darcy gave" + txtEnemyDMG + " damage to " + enemyName);
                    txtEnemyHP.setText(String.valueOf(enemyHP));
                    btnSlash.setText("Wickham's turn\nPress to proceed");
                    turnNumber++;
                    if (enemyHP == 0) {
                        mrdarcy.setVisibility(View.INVISIBLE);
                        turnNumber = 1;
                        heroHP = 1000;
                        enemyHP = 950;
                        txtCombatLog.setText("Mr Darcy gave"+ txtHeroDMG+ " damage to "+ enemyName+ ". Elizabeth is mine, you rake!");
                        btnSlash.setText("Rematch?");
                    }
                }
                else if (turnNumber%2 != 1) {
                    mrdarcy.setVisibility(View.VISIBLE);
                    mrwickham.setVisibility(View.VISIBLE);
                    heroHP = Math.max(0, heroHP - txtEnemyDMG);
                    txtCombatLog.setText(enemyName + "gave" + txtEnemyDMG + "damage to" + heroName);
                    txtHeroHP.setText(String.valueOf(heroHP));
                    btnSlash.setText("Slash");
                    turnNumber++;
                    if (heroHP == 0) {
                        mrwickham.setVisibility(View.INVISIBLE);
                        turnNumber = 1;
                        heroHP = 1000;
                        enemyHP = 950;
                        txtCombatLog.setText(enemyName+ " gave"+ txtEnemyDMG+ " damage to "+ heroName+ ". I won Elizabeth like I won your sister!");
                        btnSlash.setText("Rematch?");
                    }
                }
                break;
        }


    }
}


