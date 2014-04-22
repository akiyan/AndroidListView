package de.vogella.android.listview.app;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by akiyan on 2014/04/18.
 */
public class CustomListItemAdapter extends ArrayAdapter<HashMap> {

    private LayoutInflater mLayoutInflater;

    public CustomListItemAdapter(Context context, List<HashMap> objects) {
        super(context, 0, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.custom_list_item, parent, false);
        } else {
            view = convertView;
        }

        HashMap item = getItem(position);

        TextView text1 = (TextView) view.findViewById(R.id.TitleText);
        text1.setText("Title:" + item.get("title"));
        TextView text2 = (TextView) view.findViewById(R.id.SubTitleText);
        text2.setText("SubTitle:" + item.get("subtitle"));

        return view;
    }
}
