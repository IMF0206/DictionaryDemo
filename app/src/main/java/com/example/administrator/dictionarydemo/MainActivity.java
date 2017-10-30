package com.example.administrator.dictionarydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readAllDictory();
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

    public void lookup(View view) {
        EditText wordText = (EditText) findViewById(R.id.edit_text);
        String word = wordText.getText().toString();

        if (this.dictory == null) {
            readAllDictory();
        }
        String explain = this.dictory.get(word);

        TextView textView = (TextView) findViewById(R.id.explanation_text);
        if (explain == null) {
            textView.setText("The word is not in the dictory");
        } else {
            textView.setText(explain);
        }

    }

    private HashMap<String, String> dictory;
}
