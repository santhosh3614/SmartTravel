package com.transpo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.transpo.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(2);
        gridView.setAdapter(new HomeGridAdapter());
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            startActivity(new Intent(this, BusDetailsActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(this, BusLocationActivity.class));
        } else if (position == 2) {
            startActivity(new Intent(this, MmtsTrainSearchActivity.class));
        } else if (position == 3) {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"admin@hotmail.com"});//TODO your emaila
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
        } else if (position == 4) {
            //TODO
            //startActivity(new Intent(this,NoteEditActivity.class));
        }
    }

    private class HomeGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.grid_item, parent, false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.itemIV);
            TextView itemTextView = (TextView) convertView.findViewById(R.id.itemTV);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.bus_details_selector);
                    itemTextView.setText("Bus Details");
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.from_to_selector);
                    itemTextView.setText("Location");
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.bus_details_selector);
                    itemTextView.setText("Metro");
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.feedback_selector);
                    itemTextView.setText("Feedback");
                    break;
                case 4:
                    imageView.setImageBitmap(null);
                    itemTextView.setText("Add Note");
                    break;
            }
            return convertView;
        }
    }

}
