package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mmediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        // Create an arrayLists of words
        ArrayList<Word> family_members = new ArrayList<Word>();

        // Add numbers in arraylist
        family_members.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        family_members.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family_members.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        family_members.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family_members.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family_members.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family_members.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        family_members.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family_members.add(new Word("grandmother ","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family_members.add(new Word("grandfather ","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));






        WordAdapter adapter = new WordAdapter(this, family_members,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=family_members.get(position);
                mmediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getAudioResourceId());
                mmediaPlayer.start();
            }
        });








    }
}