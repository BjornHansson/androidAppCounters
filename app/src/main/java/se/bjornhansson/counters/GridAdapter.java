package se.bjornhansson.counters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom grid adapter to show the counters in a grid view.
 */
public class GridAdapter extends BaseAdapter {
    
    private Context myContext;
    private ArrayList<String> myList = new ArrayList<>();

    public GridAdapter(Context context, ArrayList<String> data) {
        myContext = context;
        myList = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            String itemValue = getItem(position);

            // get the layout to use
            if (itemValue.equals(myContext.getString(R.string.high_nr))) {
                gridView = inflater.inflate(R.layout.counter_high, null);
            } else if (itemValue.equals(myContext.getString(R.string.medium_nr))) {
                gridView = inflater.inflate(R.layout.counter_medium, null);
            } else {
                gridView = inflater.inflate(R.layout.counter_low, null);
            }

            // set value into the text view
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item);
            textView.setText(itemValue);

        } else {
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public String getItem(int position) {
        return myList.get(position);
    }

    public void add(String object) {
        myList.add(object);
    }

    @Override
    public long getItemId(int position) {
        // Not used
        return 0;
    }

    public void removeItem(int position) {
        myList.remove(position);
    }
}
