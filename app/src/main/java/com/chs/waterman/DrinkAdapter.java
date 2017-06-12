package com.chs.waterman;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkAdapter extends BaseAdapter {

    private final Context mContext;
    private final Drink[] drinks;

    // 1
    public DrinkAdapter(Context context, Drink[] drinks) {
        this.mContext = context;
        this.drinks = drinks;
    }

    // 2
    @Override
    public int getCount() {
        return drinks.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final Drink d= drinks[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.gridview_drinks, null);
        }

        // 3
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
        final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_author);
        final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

        // 4
        nameTextView.setText(String.valueOf( d.amount) + " " + d.getUnit());
        //imageView.setImageResource(d.getDrinkIcon());
        Beverage bev = Beverage.findById(Beverage.class, d.getBevID());

        if (bev!=null) {
            Resources res = mContext.getResources();
            String mDrawableName = bev.getBevIcon();

            int resID = res.getIdentifier(mDrawableName , "drawable",  BuildConfig.APPLICATION_ID);
            //img1.setImageResource(resID);

            imageView.setImageResource(resID);
        }


       // nameTextView.setText(mContext.getString(d.getAmount()));
        String sTime="";
        sTime=  d.getDateTime();
        sTime= sTime.substring(sTime.indexOf(' ') + 1);
        // 09:11:56 AM
        sTime = sTime.substring(0,5) + ' ' + sTime.substring(9,11);


        authorTextView.setText(sTime.replaceFirst("^0",""));

        return convertView;
    }

}