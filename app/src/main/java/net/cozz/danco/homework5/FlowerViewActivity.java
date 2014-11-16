package net.cozz.danco.homework5;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;


public class FlowerViewActivity extends Activity {
    //get the array of flag IDs
    public Integer[] flowerIds = {
            R.drawable.alabama_flower, R.drawable.alaska_flower, R.drawable.arizona_flower, R.drawable.arkansas_flower,
            R.drawable.california_flower, R.drawable.colorado_flower, R.drawable.connecticut_flower, R.drawable.delaware_flower,
            R.drawable.florida_flower, R.drawable.georgia_flower, R.drawable.hawaii_flower, R.drawable.idaho_flower,
            R.drawable.illinois_flower, R.drawable.indiana_flower, R.drawable.iowa_flower, R.drawable.kansas_flower,
            R.drawable.kentucky_flower, R.drawable.louisiana_flower, R.drawable.maine_flower, R.drawable.maryland_flower,
            R.drawable.massachusetts_flower, R.drawable.michigan_flower, R.drawable.minnesota_flower,
            R.drawable.mississippi_flower, R.drawable.missouri_flower, R.drawable.montana_flower,
            R.drawable.nebraska_flower, R.drawable.nevada_flower, R.drawable.new_hampshire_flower,
            R.drawable.new_jersey_flower, R.drawable.new_mexico_flower, R.drawable.new_york_flower,
            R.drawable.north_carolina_flower, R.drawable.north_dakota_flower, R.drawable.ohio_flower,
            R.drawable.oklahoma_flower, R.drawable.oregon_flower, R.drawable.pennsylvania_flower,
            R.drawable.rhode_island_flower, R.drawable.south_carolina_flower, R.drawable.south_dakota_flower,
            R.drawable.tennessee_flower, R.drawable.tennessee_flower, R.drawable.utah_flower, R.drawable.vermont_flower,
            R.drawable.virginia_flower, R.drawable.washington_flower, R.drawable.west_virginia_flower,
            R.drawable.wisconsin_flower, R.drawable.wyoming_flower
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_view);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        final int actionBarColor = getResources().getColor(R.color.action_bar);
        actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

        final List<String> flowers =
                Arrays.asList(getResources().getStringArray(R.array.flowers));
        Intent intent = getIntent();
        final int position = intent.getExtras().getInt("position");

        setTitle(String.format("%s", flowers.get(position)));

        ImageView imageView = (ImageView) findViewById(R.id.flower_image);
        imageView.setImageResource(flowerIds[position]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flower_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
