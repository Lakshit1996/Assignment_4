package com.example.lakshit.assignment_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickCallback{

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_NOTE = "EXTRA_NOTE";
    private static final String TAG = "LIST";
    private static final String TAG1 = "NOTES";
    private static final String TAG2 ="ICONS" ;

    private RecyclerView recView;
    private Adapter adapter;
    private ArrayList listData;
    public static ArrayList<String> titles_list = new ArrayList<String>();
    public static ArrayList<String> notes_list = new ArrayList<String>();
    public static ArrayList<Integer> icons_list=new ArrayList<Integer>();
    public static ArrayList<Integer> icons_list2=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        SharedPreferences sharedPrefs=getSharedPreferences("MyData", Context.MODE_PRIVATE);
//        if (!sharedPrefs.contains("KEY_FIRST_RUN")) {
            titles_list.add("DMG ASSIGNMENT");
            titles_list.add("VPM ASSIGNMENT");
            titles_list.add("MC ASSIGNMENT");
            titles_list.add("MC PROJECT");
            titles_list.add("ML PROJECT");
            titles_list.add("ML ASSIGNMENT");
            notes_list.add("Do it by next Monday.");
            notes_list.add("Do it by next Monday.");
            notes_list.add("Do it by next Monday.");
            notes_list.add("Do it by next Monday.");
            notes_list.add("Do it by next Monday.");
            notes_list.add("Do it by next Monday.");
            icons_list.add(R.drawable.ic_business_center_black_36dp);
            icons_list.add(R.drawable.ic_note_black_36dp);
            icons_list.add( R.drawable.ic_school_black_36dp);
            icons_list.add(R.drawable.ic_business_center_black_36dp);
            icons_list.add(R.drawable.ic_note_black_36dp);
            icons_list.add( R.drawable.ic_school_black_36dp);


//        } else {
//
//            Gson gson = new Gson();
//            String json = sharedPrefs.getString(TAG, null);
//            String json1 = sharedPrefs.getString(TAG1, null);
//            String json2= sharedPrefs.getString(TAG2, null);
//            Type type = new TypeToken<ArrayList<String>>() {}.getType();
//            titles_list= gson.fromJson(json, type);
//            notes_list = gson.fromJson(json1, type);
//            StringTokenizer st = new StringTokenizer(json2, ",");
//            int mysize = sharedPrefs.getInt("your_int_key", -1);
//
////        int[] savedList = new int[10];
//            for (int i = 0; i < mysize; i++) {
//                icons_list2.add(Integer.parseInt(st.nextToken()));
//            }
//            icons_list = (ArrayList<Integer>)icons_list2.clone();
//        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listData = (ArrayList) Data.getListData();
        recView = (RecyclerView)findViewById(R.id.rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(Data.getListData(), this);
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch   (item.getItemId())
        {
        case R.id.menu_item_new_item:
            addItem();

            return true;
            default:
            return super.onOptionsItemSelected(item);
    }
    }

    private void addItem() {
       Intent  intent=new Intent(this,Main3Activity.class);
//        startActivity(intent);
        startActivityForResult(intent,1);
//        ListItem item = Data.getRandomListItem();
//        listData.add(item);
////                Data.add_data(item);
//        adapter.notifyItemInserted(listData.indexOf(item));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("title_1");
                String result2=data.getStringExtra("note_1");
                ArrayList data_add;
                data_add= (ArrayList) Data.getListData();
//                ListItem item = Data.getRandomListItem();
//                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                ListItem item = new ListItem();
                item.setImageResId( R.drawable.ic_note_black_36dp);
                item.setTitle(result);
                item.setNote(result2);
                int rand = new Random().nextInt(3);
                int i=icons_list.get(rand);
                titles_list.add(result);
                notes_list.add(result2);
                icons_list.add(i);
                item.setImageResId(i);
                listData.add(item);
//                Data.add_data(item);
                adapter.notifyItemInserted(listData.indexOf(item));
            }
        }
    }




    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent intent = new Intent(this, Main2Activity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_TITLE, item.getTitle());
        extras.putString(EXTRA_NOTE, item.getNote());
        extras.putInt("Pos",p);
        intent.putExtra(BUNDLE_EXTRAS, extras);

        Toast.makeText(getApplicationContext(), item.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }

    @Override
    public void onCheckClick(int p) {

        ListItem item = (ListItem) listData.get(p);
        if (item.isCheck()){
            item.setCheck(false);
        } else {
            item.setCheck(true);
        }
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onStart()
    {
        super.onStart();

    }

    @Override
    public void onPause()
    {
        super.onPause();
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences sharedPrefs=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(titles_list);
        String json1=gson.toJson(notes_list);

        Integer []list = new Integer[icons_list.size()];
        icons_list.toArray(list);
//        int[] list = icons_list;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            str.append(list[i]).append(",");
        }
        String json2=gson.toJson(list);
//        sharedPrefs.edit().putString("string", str.toString());

        editor.putString(TAG, json);
        editor.putString(TAG1, json1);
        editor.putString(TAG2,json2);
        editor.putInt("your_int_key", icons_list.size());
        editor.commit();

    }

    @Override
    public void onResume(){
        super.onResume();


    }

    @Override
    public void onStop(){
        super.onStop();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }
}


