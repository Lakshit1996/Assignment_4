package com.example.lakshit.assignment_4;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lakshit.assignment_4.Pager;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private TextView title;
    private TextView note;
    private ArrayList listData;
    float x1,x2;
    float y1, y2;
    int position;
    String str_title;
    String str_note;

    View View;

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_NOTE  = "EXTRA_NOTE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        title=(TextView)findViewById(R.id.title_2);
        note=(TextView)findViewById(R.id.note_2);
        str_note=extras.getString(EXTRA_NOTE);
        str_title=extras.getString(EXTRA_TITLE);
        title.setText(str_title);
        note.setText(str_note);
        position=extras.getInt("Pos");
        listData = (ArrayList) Data.getListData();
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        View.setOnTouchListener(new Pager(Main2Activity.this) {
//            public void onSwipeRight() {
//                Toast.makeText(Main2Activity.this, "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(Main2Activity.this, "left", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch   (item.getItemId())
        {
            case R.id.menu_item_back:
                NavUtils.navigateUpFromSameTask(this);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < x2)
                {
                    if(listData.size()>position && position>=0)
                    {
                        ListItem item = (ListItem) listData.get(position);
                        Intent intent = new Intent(this, Main2Activity.class);
                        this.overridePendingTransition(0, 0);
                        Bundle extras = new Bundle();
                        extras.putString(EXTRA_TITLE,item.getTitle());
                        extras.putString(EXTRA_NOTE,item.getNote());
                        extras.putInt("Pos",position-1);
                        intent.putExtra(BUNDLE_EXTRAS, extras);
                        startActivity(intent);
//                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    }
                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    if(listData.size()>position && position>=0) {
                        ListItem item = (ListItem) listData.get(position);
                        Intent intent = new Intent(this, Main2Activity.class);
                        this.overridePendingTransition(0, 0);
                        Bundle extras = new Bundle();
                        extras.putString(EXTRA_TITLE, item.getTitle());
                        extras.putString(EXTRA_NOTE, item.getNote());
                        extras.putInt("Pos", position + 1);
                        intent.putExtra(BUNDLE_EXTRAS, extras);
                        startActivity(intent);
//                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    }
                }

//                // if UP to Down sweep event on screen
//                if (y1 < y2)
//                {
//                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
//                }
//
//                //if Down to UP sweep event on screen
//                if (y1 > y2)
//                {
//                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
//                }
                break;
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


}
