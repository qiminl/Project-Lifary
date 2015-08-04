package com.example.liuqimin.lifary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class UserProfileActivity extends Activity {


    TextView usrProfile;
    ImageView usrProtraite;
    ImageView qrCode;
    Button newDiaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        usrProfile = (TextView) findViewById(R.id.usernameProfileText);
        usrProtraite = (ImageView) findViewById(R.id.portraitImg);
        qrCode = (ImageView) findViewById(R.id.QRcodeImg);
        int userID;
        String username = null;
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                userID = extras.getInt("USER_ID");
                MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
                User user = myDBHandler.findUserByID(userID);
                username = user.getUsername();
                usrProfile.setText(user.getUsername());
            }
        }
        newDiaryButton = (Button) findViewById(R.id.newDiaryButton);
        newDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EditDiaryActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
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
}
