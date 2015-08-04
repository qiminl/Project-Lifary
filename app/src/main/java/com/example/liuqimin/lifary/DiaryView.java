package com.example.liuqimin.lifary;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class DiaryView extends Activity implements View.OnClickListener, Communication{

    Diary diary;
    TextView date, share, content, location;
    ImageView img;
    ImageButton audioPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_view);

        date = (TextView) findViewById(R.id.timeTextView);
        share = (TextView) findViewById(R.id.shareTextView);
        content = (TextView) findViewById(R.id.diaryTextView);
        location = (TextView) findViewById(R.id.locationTextView);
        img = (ImageView) findViewById(R.id.diaryImageView);
        audioPlay = (ImageButton) findViewById(R.id.playAudioButton);
        audioPlay.setOnClickListener(this);

        final TextView mTextCondition = (TextView) findViewById(R.id.textViewCondition);

        diary = null;

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            String da = extra.getString("DIARY_DATE");
            DiaryDBHandler myDiaryDBHandler = new DiaryDBHandler(this, null, null, 1);
            try {
                diary = myDiaryDBHandler.findDiaryByTime(da);
                Log.d("Lifary", "found diary");
            }catch (Exception e){
                Log.d("Lifary", "diary found ERROR: " + e.getLocalizedMessage());
            }
        }

        if(diary != null){
            /*
            TODO modify the read method.
            original
            root ref of firebase db
            */
            Firebase ref2 = new Firebase("https://kimmyblog.firebaseio.com/1");
            User alan = new User(123, "hoho","ho hoho hohoho");
            ref2.setValue(alan);
            Firebase ref3 = new Firebase("https://kimmyblog.firebaseio.com/2");
            User alan3 = new User(124,"hohaso","ho hohosdf hohoho");
            ref3.setValue(alan3);
            Firebase ref4 = new Firebase("https://kimmyblog.firebaseio.com/");
            ref4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("Lifary", "temp There are " + snapshot.getChildrenCount() + " blog posts");
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        User post = postSnapshot.getValue(User.class);
                        Log.d("Lifary", post.getId() + " - " + post.getPassword() +" - " +post.getUsername());
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.d("Lifary", "The read failed: " + firebaseError.getMessage());
                }
            });



            Firebase ref1 = new Firebase("https://docs-examples.firebaseio.com/web/saving-data/fireblog/posts");
            // Attach an listener to read the data at our posts reference
            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("Lifary", "There are " + snapshot.getChildrenCount() + " blog posts");
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        BlogPost post = postSnapshot.getValue(BlogPost.class);
                        Log.d("Lifary", post.getAuthor() + " - " + post.getTitle());
                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.d("Lifary", "The read failed: " + firebaseError.getMessage());
                }
            });

            Firebase ref = new Firebase("https://shining-heat-3017.firebaseio.com/");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("Lifary", "there are #" + snapshot.getChildrenCount() + " users");
                    for (DataSnapshot postSnapshot: snapshot.getChildren()){
                        //postSnapshot;
                    }


                    //User post = snapshot.getValue(User.class);
                    //Log.d("Lifary", post.getUserName() + " - " + post.getID());
                    /*
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        User post = postSnapshot.getValue(User.class);
                        Log.d("Lifary",post.getUserName() + " - " + post.getID());
                    }*/
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    //System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

            ref.child("hoho").child("password").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("Lifary", ((String)dataSnapshot.getValue()));
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            date.setText(diary.getDate());      // set date
            share.setText(""+diary.getShare()); // set share status
            content.setText(diary.getContent());    // set diary text

            // get location
            float longitude = diary.getLongitude();
            float latitude = diary.getLatitude();

            Communication cc= (Communication) this;
            ReadLocation readLocation = new ReadLocation(this, cc);
            readLocation.getLocation(""+latitude, ""+longitude);

            // get image
            try{
                byte[] blob = diary.getImg();


            }catch (Exception e){

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diary_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void com(String contents) {
        share.setText(contents);
    }
}
