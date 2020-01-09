package com.sandesh.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.sandesh.hamrobazar.Condtions.AdActivity;
import com.sandesh.hamrobazar.Condtions.SafetyActivity;
import com.sandesh.hamrobazar.Condtions.TermsActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox chTerms, chSafety, chAd;
    Button btnAgree;
    String terms,safety,ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        chTerms = findViewById(R.id.chTerms);
        chSafety = findViewById(R.id.chSafety);
        chAd = findViewById(R.id.chAd);
        btnAgree = findViewById(R.id.btnAgree);

        chTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(),TermsActivity.class);
                startActivity(intent);
                terms="checked";
            }
        });

        chSafety.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), SafetyActivity.class);
                startActivity(intent);
                safety="checked";
            }
        });
        chAd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(),AdActivity.class);
                startActivity(intent);
                ad="checked";
            }
        });


        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Agree();
            }
        });
    }

    private void Agree() {
        if (!chTerms.isChecked())
        {
            Toast.makeText(this, "Terms is not checked", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!chSafety.isChecked())
        {
            Toast.makeText(this, "Safety is not checked", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!chAd.isChecked())
        {
            Toast.makeText(this, "AD is not checked", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            SharedPreferences sharedPreferences=getSharedPreferences("welcome",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putString("terms",terms);
            editor.putString("safety",safety);
            editor.putString("ad",ad);
            editor.commit();

            Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
            startActivity(intent);

        }

    }
}
