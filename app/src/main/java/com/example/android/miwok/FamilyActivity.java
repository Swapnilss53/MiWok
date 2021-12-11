package com.example.android.miwok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.MediaRouteButton;
import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FamilyActivity<audioManager, mAudioManager> extends AppCompatActivity {

    private MediaPlayer mmediaPlayer;
    private AudioManager mAudioManager;


    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                       releaseMediaPlayer();

                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        // Pause playback
                        mmediaPlayer.pause();
                        mmediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mmediaPlayer.start();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.i("Completion Listener","song completed");
            Toast.makeText(FamilyActivity.this, "Media Completed", Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
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
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);



                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    mmediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
                    mmediaPlayer.start();
                    mmediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mmediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mmediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mmediaPlayer = null;

            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
        Log.i("mdeia releses","media released");
    }


}