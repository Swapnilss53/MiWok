package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Find the view that shows the number category
        TextView numbers = (TextView)findViewById(R.id.numbers);

        //set a clicklistener to that view
        numbers.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent numbersIntent = new Intent(MainActivity.this,NumbersActivity.class);

                startActivity(numbersIntent);
            }
        });
        // Find the view that shows the Phrases category
        TextView phrases = (TextView)findViewById(R.id.phrases);

        //set a clicklistener to that view
        phrases.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent phrasesIntent = new Intent(MainActivity.this,PhrasesActivity.class);

                startActivity(phrasesIntent);
            }
        });
        // Find the view that shows the FamilyMember category
        TextView familymMember = (TextView)findViewById(R.id.family);

        //set a clicklistener to that view
        familymMember.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);

                startActivity(familyIntent);
            }
        });
        // Find the view that shows the Colors category
        TextView colors = (TextView)findViewById(R.id.colors);

        //set a clicklistener to that view
        colors.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent colorsIntent = new Intent(MainActivity.this,ColorsActivity.class);

                startActivity(colorsIntent);
            }
        });





    }

}