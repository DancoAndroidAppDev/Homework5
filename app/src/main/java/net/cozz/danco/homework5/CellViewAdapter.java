package net.cozz.danco.homework5;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by costd035 on 10/30/14.
 */
public class CellViewAdapter extends BaseAdapter {
    private final static String TAG = CellViewAdapter.class.getCanonicalName();

    private final MyActivity context;
    private final List<String> states;

    boolean isFirstView = true;
    private final int[] colors = new int[50];

    // Constructor
    public CellViewAdapter(MyActivity context){
        this.context = context;
        states = Arrays.asList(context.getResources().getStringArray(R.array.states));
    }


    @Override
    public int getCount() {
        return states.size();
    }


    @Override
    public Object getItem(int i) {
        return states.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.cell_layout, null);

            Random r = new Random();
            if (isFirstView) {
                isFirstView = false;
                for (int i = 0; i < colors.length; i++) {
                    colors[i] = Color.rgb(r.nextInt(192), r.nextInt(192), r.nextInt(192));
                }
            }
            int backgroundColor = colors[position];
            int textColor = backgroundColor < Integer.MAX_VALUE / 2 ?
                    backgroundColor * 2 :
                    backgroundColor / 2;
            view.setBackgroundColor(backgroundColor);

            viewHolder = new ViewHolder();

            TextView textView = (TextView) view.findViewById(R.id.state_cell_row1);
            textView.setText(states.get(position));
            // I've tried several algorithms here to try to pick a good color contrast --
            //  is there a recommended method?
            if (!isFirstView) {
                textView.setTextSize(dp2Px(context.getFontSize()));
            }
            textView.setTextColor(Integer.MAX_VALUE);
            textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            textView.setHeight(dp2Px(56));
            viewHolder.stateName = textView;

//            View leftBox = view.findViewById(R.id.leftBox);
//            leftBox.setBackgroundColor(r.nextInt());
//            viewHolder.leftBox = leftBox;
//
//            View right = view.findViewById(R.id.rightBox);
//            right.setBackgroundColor(r.nextInt());
//            right.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
//            viewHolder.rightBox = right;

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.stateName.setText(states.get(position));

        return view;
    }


    public int dp2Px(int dp){
//        Log.i(TAG, "dp2Px");

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    static class ViewHolder {
        TextView stateName;
        View leftBox;
        View rightBox;
    }
}
