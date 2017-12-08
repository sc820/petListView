package edu.nsysu.petlistview;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Frank on 2017/12/6.
 */

public class Pet {
    public static final String TAG = "Pet";

    public String title;
    public String description;
    public String imageUrl;
    public String linkUrl;
    public String label;

    public static ArrayList<Pet> getPetsFromFile(String filename, Context context){
        final ArrayList<Pet> petList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("pets.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray pets = json.getJSONArray("pets");

            // Get pets objects from data
            for(int i = 0; i < pets.length(); i++){
                Pet pet = new Pet();

                pet.title = pets.getJSONObject(i).getString("title");
                pet.description = pets.getJSONObject(i).getString("description");
                pet.imageUrl = pets.getJSONObject(i).getString("image");
                pet.linkUrl = pets.getJSONObject(i).getString("url");
                pet.label = pets.getJSONObject(i).getString("label");

                petList.add(pet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return petList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
