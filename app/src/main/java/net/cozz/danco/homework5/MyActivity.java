package net.cozz.danco.homework5;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MyActivity extends Activity {
    public static final String TAG = MyActivity.class.getCanonicalName();

    private int fontSize = 4;

    private BaseAdapter adapter = null;

    public int getFontSize() {
        return fontSize;
    }

    private GridView gridView;

    private Random rand = new Random();
    private final List<Integer> cellIndecies = new ArrayList<Integer>(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final String[] capitals = getResources().getStringArray(R.array.flowers);

        FlowersDataSource datasource = new FlowersDataSource(this);

        try {
            datasource.open();
            int i = 0;
            for (String state : getResources().getStringArray(R.array.states)) {
                datasource.addFlower(state, capitals[i++]);
            }
            List <Flower> capitalsList = datasource.getFlowers();
            Log.d("", capitalsList.toString());
        } catch (SQLException e) {
            Log.d("MyActivity", "unable to open datasource");
        }

        adapter = new CellViewAdapter(this);
        loadContent();
    }


    private void loadContent() {
        final List<String> capitals =
                Arrays.asList(getResources().getStringArray(R.array.capitals));

        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Capital is " + capitals.get(position), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), ViewFlagActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.increase_font) {
            fontSize += 1;
            loadContent();
            return true;
        } else if (id == R.id.decrease_font) {
            fontSize -= 1;
            loadContent();
            return true;
        } else if (id == R.id.animate) {
            doAnimate();
        } else if (id == R.id.animate_all) {
            doAnimation();
        }
        return super.onOptionsItemSelected(item);
    }


    public void doAnimate() {
        int position = rand.nextInt(gridView.getChildCount());

        Log.i(TAG, "animating cell at position: " + position);
        View view = gridView.getChildAt(position);
        cellIndecies.add(position);

        TextView textView = (TextView) view.findViewById(R.id.state_cell_row1);

        Log.i(TAG, String.format("animating: %s", textView.getText().toString()));
        rotate(textView, false);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public void doAnimation() {
        TextView textView = null;
        int position = rand.nextInt(gridView.getChildCount());
        while (cellIndecies.contains(position)) {
            position = rand.nextInt(gridView.getChildCount());
        }

        Log.i(TAG, "animating cell at position: " + position);
        View view = gridView.getChildAt(position);
        if (view.isEnabled()) {
            cellIndecies.add(position);
            if (cellIndecies.size() == 50) {
                cellIndecies.clear();
            }
            textView = (TextView) view.findViewById(R.id.state_cell_row1);
//        view = (TextView) findViewById(R.id.state_cell_row1);

            Log.i(TAG, String.format("animating: %s", textView.getText().toString()));
            rotate(textView, true);
        } else {
            doAnimation();
        }
    }


    public void animateColor(TextView textView){
        final int RED = 0xffFF8080;
        final int BLUE = 0xff8080FF;

        ValueAnimator colorAnim = ObjectAnimator.ofInt(textView, "textColor", RED, BLUE);
        colorAnim.setDuration(1000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

    }


    public void rotate(final TextView textView, final boolean cycle){
        Animation animation;

        List<Integer> animations = new ArrayList<Integer>(4);
        animations.add(R.anim.blinking);
        animations.add(R.anim.fade_in);
        animations.add(R.anim.fade_out);
        animations.add(R.anim.rotate);
        animations.add(R.anim.color);

        animation =
                AnimationUtils.loadAnimation(this, animations.get(rand.nextInt(animations.size())));

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "animation ended");
                if (cycle) {
                    doAnimation();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                textView.clearAnimation();
                Log.i(TAG, "animation repeating");
            }
        });

        textView.startAnimation(animation);
    }


    public void fadeInOut(final TextView textView){
        Animation animFadeIn, animFadeOut;

        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                continueAnim(textView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                doAnimation();
            }
        });

        textView.startAnimation(animFadeIn);
    }



    public void continueAnim(final TextView textView){
        Animation animFadeOut;

        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        textView.startAnimation(animFadeOut);
    }
}
