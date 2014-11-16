package net.cozz.danco.homework5;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class ViewFlagActivity extends Activity {

    //get the array of flag IDs
    public Integer[] flagsIds = {
            R.drawable.alabama, R.drawable.alaska, R.drawable.arizona, R.drawable.arkansas,
            R.drawable.california, R.drawable.colorado, R.drawable.connecticut, R.drawable.delaware,
            R.drawable.florida, R.drawable.georgia, R.drawable.hawaii, R.drawable.idaho,
            R.drawable.illinois, R.drawable.indiana, R.drawable.iowa, R.drawable.kansas,
            R.drawable.kentucky, R.drawable.louisiana, R.drawable.maine, R.drawable.maryland,
            R.drawable.massachusetts, R.drawable.michigan, R.drawable.minnesota,
            R.drawable.mississippi, R.drawable.missouri, R.drawable.montana,
            R.drawable.nebraska, R.drawable.nevada, R.drawable.new_hampshire,
            R.drawable.new_jersey, R.drawable.new_mexico, R.drawable.new_york,
            R.drawable.north_carolina, R.drawable.north_dakota, R.drawable.ohio,
            R.drawable.oklahoma, R.drawable.oregon, R.drawable.pennsylvania,
            R.drawable.rhode_island, R.drawable.south_carolina, R.drawable.south_dakota,
            R.drawable.tennessee, R.drawable.tennessee, R.drawable.utah, R.drawable.vermont,
            R.drawable.virginia, R.drawable.washington, R.drawable.west_virginia,
            R.drawable.wisconsin, R.drawable.wyoming
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);


        // Inflate your custom layout
        final ViewGroup actionBarLayout =
                (ViewGroup) getLayoutInflater().inflate(R.layout.action_bar, null);

        // Set up your ActionBar
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        // You customization
        final int actionBarColor = getResources().getColor(R.color.action_bar);
        actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

        final List<String> capitals =
                Arrays.asList(getResources().getStringArray(R.array.capitals));

        final List<String> states =
                Arrays.asList(getResources().getStringArray(R.array.states));
        Intent intent = getIntent();
        final int position = intent.getExtras().getInt("position");

        setTitle(String.format("%s State flag", states.get(position)));

        ImageView imageView = (ImageView) findViewById(R.id.flag_image);
        imageView.setImageResource(flagsIds[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),
//                        "Capital is " + capitals.get(position), Toast.LENGTH_LONG).show();

                final WebView webView = (WebView) findViewById(R.id.activity_my_web_view);
//                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
//                String term = "capital:" + states.get(position);
//                i.putExtra(SearchManager.QUERY, term);
//
//                startActivity(i);
            }
        });

        TextView textView = (TextView) findViewById(R.id.label);
        textView.setText(String.format("%s State flag", states.get(position)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.flag, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.show_flower) {
            Intent intent = new Intent(getApplicationContext(), FlowerViewActivity.class);
            intent.putExtra("position", getIntent().getExtras().getInt("position"));
            startActivity(intent);
        }
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
