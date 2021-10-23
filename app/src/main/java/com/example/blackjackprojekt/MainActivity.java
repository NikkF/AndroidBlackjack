package com.example.blackjackprojekt;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public ImageView startcard1, startcard2;
    public int totalcards, totaldealercards;
    public TextView txt, resulttxt;
    public Button stand, hit, reset;
    final int min = 1;
    final int max = 13;
    int hitnumber = 1;
    int id;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.total);
        stand = findViewById(R.id.stand);
        reset = findViewById(R.id.reset);
        hit = findViewById(R.id.hit);
        resulttxt = (TextView) findViewById(R.id.result);

        c = getApplicationContext();

        //STARTING CARDS

        //CARD 1
        int card1 = new Random().nextInt((max - min) + 1) + min;
        startcard1 = (ImageView) findViewById(R.id.startcard1);
        id = c.getResources().getIdentifier("drawable/"+"c" + card1, null, c.getPackageName());
        startcard1.setImageResource(id);

        //CARD 2
        int card2 = new Random().nextInt((max - min) + 1) + min;
        startcard2 = (ImageView) findViewById(R.id.startcard2);
        id = c.getResources().getIdentifier("drawable/"+"c" + card2, null, c.getPackageName());
        startcard2.setImageResource(id);

        //TOTAL STARTING CARDS
        totalcards = card1 + card2;
        txt.setText("" + totalcards);

        //DEALER CARDS
        int dealer1 = new Random().nextInt((max - min) + 1) + min;
        int dealer2 = new Random().nextInt((max - min) + 1) + min;
        totaldealercards = (dealer1 + dealer2) + 4;
        if(totaldealercards >= 21)
            totaldealercards = 20;
    }

    public void Stand(View v){
        if(totalcards > totaldealercards)
            resulttxt.setText("THE DEALER HAS A TOTAL OF " + totaldealercards + " CARDS, YOU WIN!");

        else if(totalcards < totaldealercards)
            resulttxt.setText("THE DEALER HAS A TOTAL OF " + totaldealercards + " CARDS, YOU LOSE!");

        else if(totalcards == totaldealercards)
            resulttxt.setText("THE DEALER HAS A TOTAL OF " + totaldealercards + " CARDS, DRAW!");

        hit.setClickable(false);
        stand.setClickable(false);
    }

    public void Hit(View v){

        //NEW CARD
        if(hitnumber < 4)
        {
            int newcardnumber = new Random().nextInt((max - min) + 1) + min;

            //GET NEW CARD
            int resID = getResources().getIdentifier("startcard" + (hitnumber + 2), "id", getPackageName());
            ImageView newcard = (ImageView) findViewById(resID);

            //DISPLAY NEW CAR
            id = c.getResources().getIdentifier("drawable/"+"c" + newcardnumber, null, c.getPackageName());
            newcard .setImageResource(id);
            newcard.setVisibility(View.VISIBLE);

            totalcards += newcardnumber;
            txt.setText("" + totalcards);
            hitnumber++;
        }

        //CHECK FOR BUST OR BLACKJACK
        if(totalcards > 21) {
            resulttxt.setText("BUST!");
            hit.setClickable(false);
            stand.setClickable(false);
        }
        else if(totalcards == 21) {
            resulttxt.setText("BLACKJACK! YOU WIN!");
            hit.setClickable(false);
            stand.setClickable(false);
        }
    }

    public void Reset(View v){
        finish();
        startActivity(getIntent());
    }

    //NIKOLA FILIPOVIĆ
}
