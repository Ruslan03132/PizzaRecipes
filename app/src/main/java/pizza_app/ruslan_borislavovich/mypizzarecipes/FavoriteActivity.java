package pizza_app.ruslan_borislavovich.mypizzarecipes;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    Toolbar toolbar;
    public ArrayList <InfoCardItem> favoriteArrayItems;
    FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadData();


        RecyclerView recyclerViewFavorite = findViewById(R.id.recyclerViewFavorite);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        favoriteAdapter = new FavoriteAdapter(favoriteArrayItems, this);
        recyclerViewFavorite.setLayoutManager(manager);
        recyclerViewFavorite.setAdapter(favoriteAdapter);


    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoritelist", null);
        Type type = new TypeToken<ArrayList<InfoCardItem>>(){}.getType();
        favoriteArrayItems = gson.fromJson(json, type);
        if (favoriteArrayItems == null){
            favoriteArrayItems = new ArrayList<>();
        }
    }




}
