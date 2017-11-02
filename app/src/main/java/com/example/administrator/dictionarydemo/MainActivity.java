package com.example.administrator.dictionarydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readAllDictory();

        myactivity = this;
        this.definations = new ArrayList<>();
        this.myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, definations);
        this.wordList = new ArrayList<>(this.dictory.keySet());

        pick5defination();
        final ListView defnList = (ListView) findViewById(R.id.Defenition_List);
        defnList.setAdapter(myAdapter);

        defnList.setOnItemClickListener(this);
//        defnList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String chosenText = defnList.getItemAtPosition(position).toString();
//                if (dictory.get(word).equals(chosenText)) {
//                    Toast.makeText(myactivity, "Correct", Toast.LENGTH_SHORT).show();;
//                } else {
//                    Toast.makeText(myactivity, "Wrong", Toast.LENGTH_SHORT).show();
//                }
//                pick5defination();
//            }
//        });
//        TextView wordView = (TextView) findViewById(R.id.word);
//        wordView.setText(this.word);

    }

    private void pick5defination() {

        Collections.shuffle(wordList);
        this.word = wordList.get(0);
        definations.clear();
        for(int i = 0; i < 5; i++) {
            String candidate = wordList.get(i);
            this.definations.add(this.dictory.get(candidate));
        }

        Collections.shuffle(definations);
        myAdapter.notifyDataSetChanged();
        TextView wordView = (TextView) findViewById(R.id.word);
        wordView.setText(this.word);


    }

    private void readAllDictory() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.gre_words));
        if (this.dictory == null) {
            this.dictory = new HashMap<>();
        }
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] pair = line.split("\t");
            this.dictory.put(pair[0], pair[1]);
        }
    }


    private HashMap<String, String> dictory;
    private MainActivity myactivity;
    private ArrayList<String> wordList;
    private String word;
    private ArrayList<String> definations;
    private ArrayAdapter<String> myAdapter;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView defnList = (ListView) findViewById(R.id.Defenition_List);
        String chosenText = defnList.getItemAtPosition(position).toString();
        if (dictory.get(word).equals(chosenText)) {
            Toast.makeText(myactivity, "Correct", Toast.LENGTH_SHORT).show();;
        } else {
            Toast.makeText(myactivity, "Wrong", Toast.LENGTH_SHORT).show();
        }
        pick5defination();
    }
}
