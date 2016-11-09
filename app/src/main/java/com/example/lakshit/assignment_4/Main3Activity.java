package com.example.lakshit.assignment_4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private EditText title;
    private EditText note;
    public String t;
    public String n;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        title=(EditText)findViewById(R.id.add_title_text);
        note=(EditText)findViewById(R.id.add_note_text);
        btn=(Button)findViewById(R.id.button_add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t=title.getText().toString();
                n=note.getText().toString();
                Log.v("EditText", title.getText().toString());
//                Toast.makeText(getApplicationContext(),t,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"Successfully added.",Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("title_1",t);
                returnIntent.putExtra("note_1",n);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });


    }

//    public void add_button(View view)
//    {
//        Toast.makeText(getApplicationContext(),t,Toast.LENGTH_SHORT).show();
////        Toast.makeText(getApplicationContext(),"Successfully added.",Toast.LENGTH_SHORT).show();
//
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("title_1",t);
////        returnIntent.putExtra("note_1",n);
//        setResult(Activity.RESULT_OK,returnIntent);
//        finish();
//    }
}
