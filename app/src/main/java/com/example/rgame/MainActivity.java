package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView head;
    private TextView[] kosci = new TextView[5];
    private TextView WynikLosowania;
    private TextView WynikCalkowity;
    private TextView Rzuty;
    private Button throwButton;
    private Button resetButton;

    private int calyWynik = 0;
    private int liczbaRzutow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        head = findViewById(R.id.head);
        kosci[0] = findViewById(R.id.kosc1);
        kosci[1] = findViewById(R.id.kosc2);
        kosci[2] = findViewById(R.id.kosc3);
        kosci[3] = findViewById(R.id.kosc4);
        kosci[4] = findViewById(R.id.kosc5);
        WynikLosowania = findViewById(R.id.WynikLosowania);
        WynikCalkowity = findViewById(R.id.WynikCalkowity);
        Rzuty = findViewById(R.id.Rzuty);
        throwButton = findViewById(R.id.throwButton);
        resetButton = findViewById(R.id.resetButton);

        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        Random random = new Random();
        int[] Numery = new int[5];
        for (int i = 0; i < 5; i++) {
            Numery[i] = random.nextInt(6) + 1;
        }
        wyswietlWynikiKosci(Numery);
        int wynik = obliczWynik(Numery);
        updateScore(wynik);
        nowaLiczbaLosowan();
    }

    private int obliczWynik(int[] Numery) {
        int[] indeksy = new int[7];
        for (int result : Numery) {
            indeksy[result]++;
        }
        int  wynik = 0;
        for (int i = 1; i <= 6; i++) {
            if (indeksy[i] >= 2) {
                wynik += i * indeksy[i];
            }
        }
        return wynik;
    }

    private void resetGame() {
        for (TextView kosc : kosci) {
            kosc.setText("?");
        }
        calyWynik = 0;
        liczbaRzutow = 0;
        WynikLosowania.setText("Wynik tego losowania: 0");
        WynikCalkowity.setText("Wynik gry: 0");
        Rzuty.setText("Liczba rzutów: 0");
    }

    private void updateScore(int nowyWynik) {
        calyWynik += nowyWynik;
        WynikCalkowity.setText("Wynik gry: " + calyWynik);
        WynikLosowania.setText("Wynik tego losowania: " + nowyWynik);
    }

    private void nowaLiczbaLosowan() {
        liczbaRzutow++;
        Rzuty.setText("Liczba rzutów: " + liczbaRzutow);
    }

    private void wyswietlWynikiKosci(int[] Numery) {
        for (int i = 0; i < Numery.length; i++) {
            kosci[i].setText(String.valueOf(Numery[i]));
        }
    }
}
