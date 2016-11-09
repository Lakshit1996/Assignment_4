package com.example.lakshit.assignment_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {

//    private static ArrayList<String> titles = new ArrayList<String>();
//    private static ArrayList<String> notes = new ArrayList<String>();



    private static String[] titles = {"DMG ASSIGNMENT","VPM ASSIGNMENT","MC ASSIGNMENT","MC PROJECT","ML PROJECT","ML ASSIGNMENT"};
    private static String[] notes = {"Do it by next Monday.", "Do it by next Tuesday.", "Do it by next Wednesday.", "Do it by next Thursday.", "Do it by next Friday.", "Do it by next Saturday."};
    private static final int[] icons = {R.drawable.ic_business_center_black_36dp, R.drawable.ic_note_black_36dp, R.drawable.ic_school_black_36dp,R.drawable.ic_business_center_black_36dp, R.drawable.ic_note_black_36dp, R.drawable.ic_school_black_36dp};

    public static List<ListItem> getListData() {
        String s1 ="MC";
        String s2="Test";
//        titles.add(s1);
//        notes.add(s2);
        List<ListItem> data = new ArrayList<>();

//        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < titles.length && i < icons.length; i++) {
//                for (int i = 0; i < titles.size() && i < icons.length; i++) {
                    ListItem item = new ListItem();
                    item.setImageResId(icons[i]);
//                item.setTitle(titles.get(i));
//                item.setNote(notes.get(i));
                    item.setTitle(titles[i]);
                    item.setNote(notes[i]);
                    data.add(item);
                }

//        }
        return data;
    }

//    public static void add_data(ListItem item){
//        titles.add(item.getTitle());
//        notes.add(item.getNote());
//    }

    public static ListItem getRandomListItem(){
        int rand = new Random().nextInt(6);

        ListItem item = new ListItem();

        item.setTitle(titles[rand]);
        item.setNote(notes[rand]);

        return item;
    }


}
