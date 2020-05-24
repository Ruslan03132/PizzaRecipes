package pizza_app.ruslan_borislavovich.mypizzarecipes;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MoreActivity extends AppCompatActivity {

Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView more =  findViewById(R.id.more);
        TextView moreVersion =  findViewById(R.id.moreVersion);


        more.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);
        moreVersion.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);
    }


}
