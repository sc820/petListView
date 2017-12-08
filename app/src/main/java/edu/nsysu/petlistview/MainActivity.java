package edu.nsysu.petlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.pet_list_view);
        final ArrayList<Pet> petList = Pet.getPetsFromFile("pets.json",this);

        PetAdapter adapter = new PetAdapter(this, petList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pet selectedPet = petList.get(i);
                Intent detailIntent = new Intent(MainActivity.this, PetDetail.class);
                detailIntent.putExtra("title", selectedPet.title);
                detailIntent.putExtra("url", selectedPet.linkUrl);

                startActivity(detailIntent);

            }
        });

    }
}
