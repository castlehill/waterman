package com.chs.waterman;

import android.app.Dialog;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SESA312452 on 6/12/2017.
 */

public class EditDrinkDialog extends BottomSheetDialogFragment {
    TextView tvDrink = null;
    EditText editAmount = null;
    public Drink drink;
    ImageView imgDelete = null;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.bottom_sheet_edit_drink, null);
        dialog.setContentView(view);


        final View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//        behavior.setPeekHeight(...);
        // how to set maximum expanded height???? Or a minimum top offset?


        tvDrink = (TextView) view.findViewById(R.id.tvDrink);
        editAmount = (EditText) view.findViewById(R.id.EditAmount);
        imgDelete = (ImageView) view.findViewById(R.id.imgDeleteDrink);

        tvDrink.setText(getDrinkTime(drink));
        editAmount.setText(drink.getAmount() + "");

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drink.delete(drink);
            }
        });


    }


    String getDrinkTime(Drink d) {

        // nameTextView.setText(mContext.getString(d.getAmount()));
        String sTime = "";
        sTime = d.getDateTime();
        sTime = sTime.substring(sTime.indexOf(' ') + 1);
        // 09:11:56 AM
        sTime = sTime.substring(0, 5) + ' ' + sTime.substring(9, 11);


        sTime = (sTime.replaceFirst("^0", ""));
        return sTime;
    }
}