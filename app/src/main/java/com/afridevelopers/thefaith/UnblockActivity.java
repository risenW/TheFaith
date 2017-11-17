package com.afridevelopers.thefaith;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

public class UnblockActivity extends AppCompatActivity {
    private CardView verify,resend_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unblock);

        verify = (CardView)findViewById(R.id.verify);
        resend_code = (CardView)findViewById(R.id.resend_code);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for verification goes here
                //After verification has been confirmed, it display a dialog to reset password

                AlertDialog.Builder builder = new AlertDialog.Builder(UnblockActivity.this);
                LayoutInflater layoutinflater = getLayoutInflater();
                builder.setView(layoutinflater.inflate(R.layout.new_password,null));

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Code to Save to database and update preference
                        Intent intent = new Intent(UnblockActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }

}
