package com.example.devinette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button low,heigh,verif,Npartie;
    public RadioGroup radioGroup;
    public TextView textView,result,history,timer;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.radioGroup);
        textView =findViewById(R.id.textView);
        low =findViewById(R.id.low);
        editText=findViewById(R.id.editText);
        verif= findViewById(R.id.verif);
        history=findViewById(R.id.history);
        result=findViewById(R.id.resultat);
        Npartie=findViewById(R.id.Npartie);
        heigh =findViewById(R.id.heigh);
        timer=findViewById(R.id.timer);



        final int[] i = new int[1];
        final int[] nombre = new int[1];
        final int[] score = new int[1];
        final int[] counter = new int[1];

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(0xff00ff00);
                radioGroup.setVisibility(View.GONE);
                textView.setTypeface(null, Typeface.BOLD_ITALIC);
                textView.setText("Choose a number in [1..1000]");
                editText.setVisibility(View.VISIBLE);
                verif.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                history.setVisibility(View.VISIBLE);
                nombre[0] = 1 + (int)(Math.random() * (1000));
                result.setText(String.valueOf(nombre[0]));
                i[0] =0;
                score[0] =100;
            }
        });

        heigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.setVisibility(View.GONE);
                textView.setTypeface(null, Typeface.BOLD_ITALIC);
                textView.setText("Choose a number in [1..1000]");

                editText.setVisibility(View.VISIBLE);
                verif.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                nombre[0] = 1 + (int) (Math.random() * (1000));
                result.setText(String.valueOf(nombre[0]));
                i[0] = 0;
                score[0] = 100;
                timer.setVisibility(View.VISIBLE);
                counter[0]=0;
                new CountDownTimer(100000, 1000){
                    public void onTick(long millisUntilFinished){
                       if((score[0]<=0) ){
                           cancel();
                           i[0]++;

                           result.setText(nombre[0] +" est le nombre cherché !\n tu fais "+i[0]+" tentatives \n votre score: " +score[0] +" / 100");

                           textView.setText("C'est Perdu ! !");

                           editText.setVisibility(View.GONE);
                           verif.setVisibility(View.GONE);
                           Npartie.setVisibility(View.VISIBLE);

                       }
                        if(textView.getText().toString()=="C'est gagnant !") cancel();
                        timer.setText(String.valueOf(counter[0]));
                        counter[0]++;
                        score[0]--;
                    }
                    public  void onFinish(){
                        result.setText( "Temps fini");

                        textView.setText("Vous ets perdu  !");
                        timer.setVisibility(View.GONE);
                        editText.setVisibility(View.GONE);
                        verif.setVisibility(View.GONE);
                        Npartie.setVisibility(View.VISIBLE);

                    }
                }.start();


            }



        });
        Npartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Choose a level!");
                history.setText("Historique :");
                history.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                radioGroup.setVisibility(View.VISIBLE);
                Npartie.setVisibility(View.GONE);

            }
        });
        verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    int nbrEditText = Integer.parseInt(editText.getText().toString());

                    if (nbrEditText < 1 || nbrEditText > 1000)
                        result.setText("intervalle incorrecte");

                    else{
                        if(nbrEditText< nombre[0]) {
                            i[0]++;
                            score[0]--;
                            result.setText("le nombre chercher est superieur à " + nbrEditText);


                        }
                        else {
                            if (nbrEditText> nombre[0]) {
                                i[0]++;
                                score[0]--;
                                result.setText("le nombre chercher est inferieur à " + nbrEditText);

                            }
                            else {
                                i[0]++;

                                result.setText(nombre[0] +" est le nombre cherché !\n tu fais "+i[0]+" tentatives \n votre score: " +score[0] +" / 100");

                                textView.setText("C'est gagnant !");
                                timer.setVisibility(View.GONE);
                                editText.setVisibility(View.GONE);
                                verif.setVisibility(View.GONE);
                                Npartie.setVisibility(View.VISIBLE);
                            }
                        }
                        history.setText(history.getText().toString()+"\n tentative "+i[0]+": "+nbrEditText);
                    }
                    editText.setText("");
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}