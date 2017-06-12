package com.chs.waterman;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by SESA312452 on 5/26/2017.
 */

public class DefaultDrinkDialog extends BottomSheetDialogFragment {

    ImageView img1;
    Drink drink;

    @Override
    public void onCancel(DialogInterface dialog)
    {
        super.onCancel(dialog);
       // Toast.makeText(getActivity(), "CANCEL", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet, null);
        dialog.setContentView(view);


        final View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//        behavior.setPeekHeight(...);
        // how to set maximum expanded height???? Or a minimum top offset?



        // *******************************
        // get all the beverage selections
        // *******************************

        List<Beverage> bevs = Beverage.listAll(Beverage.class);
        int bevCount = bevs.size();

        // get the view holder
        View v = (View) dialog.findViewById(R.id.design_bottom_sheet);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linearLayout);

        // add the Beverages to pick from
        for (final Beverage b : bevs) {

            View child = dialog.getLayoutInflater().inflate(R.layout.drink_item, null);
            TextView tv = (TextView) child.findViewById(R.id.textDesc);
            tv.setText(b.getBevName());
            //tv.setText("x: " + Integer.toString(x));
            ll.addView(child);



            img1 = (ImageView) child.findViewById(R.id.ic_image1);
            img1.setImageResource(R.drawable.sports);

            try {
                Resources res = getResources();
                String mDrawableName = b.getBevIcon();
                int resID = res.getIdentifier(mDrawableName , "drawable", getActivity().getPackageName());
                img1.setImageResource(resID);


            }
            catch(Exception ex)
            {
                Log.d("setimageresource",ex.getMessage());

            }
            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //add this to the database
                    Date curDate = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String dateTime = format.format(curDate);

                    drink = new Drink(dateTime, b.getBevAmount(), b.getBevUnit(), b.getId());

                    drink.save();


                    // Right here!
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

                    ((MainActivity)getActivity()).updateProgress();

                }
            });

        }


    }


}
