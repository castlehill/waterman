package com.chs.waterman;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orm.util.NamingHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    User u;
    View bottomSheetView;
    BottomSheetDialog bottomSheetDialog;
    BottomSheetBehavior bottomSheetBehavior;
    TextView tvTarget = null;
    TextView tvTotal = null;

    ProgressBar pb = null;

    @Override
    protected void onResume() {
        super.onResume();
        //refresh the UI elements
        updateProgress();
        //initialize the beverages

    }

    public void updateProgress() {
        loadBeverages();

        showProgress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // Drink.deleteAll(Drink.class);

        //initialize the beverages
        loadBeverages();

        tvTarget = (TextView) findViewById(R.id.dayTarget);
        tvTotal = (TextView) findViewById(R.id.dayTotal);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "You drank something???", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                // display the Drink Selector

                DefaultDrinkDialog bottomSheetDialog = new DefaultDrinkDialog();
                BottomSheetBehavior mBottomSheetBehavior;

                bottomSheetDialog.show(getSupportFragmentManager(), "Custom Bottom Sheet");


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //load the user info
        try {
            long userRecordId = 1;
            u = User.findById(User.class, userRecordId);
        } catch (Exception ex) {

        }
        if (u == null) {
            User u = new User("User", "F", "120");


            u.save();
        }


        pb = (ProgressBar) findViewById(R.id.progressBar1);
        // Get the Drawable custom_progressbar
        Drawable draw = getDrawable(R.drawable.custom_progressbar);
            // set the drawable as progress drawable
        pb.setProgressDrawable(draw);
        pb.setProgress(50);
        // get all drinks

        //   List<Drink> drinks = Drink.listAll(Drink.class);
        //  int drinkCount = drinks.size();

        // Toast.makeText(this,"You have " + Integer.toString(drinkCount) + " drinks in the database!", Toast.LENGTH_SHORT).show();


        //load progress
        showProgress();


    }

    private void showProgress() {

        //IconRoundCornerProgressBar pbTarget = (IconRoundCornerProgressBar) findViewById(R.id.progressBar);
        //RoundCornerProgressBar pbTarget = (RoundCornerProgressBar) findViewById(R.id.progressBar);
        //pbTarget.setIconImageResource(R.drawable.ic_menu_camera);
        // pbTarget.setMax(86);

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = format.format(curDate);

        List<Drink> drinks = Drink.find(Drink.class, "substr(" + NamingHelper.toSQLNameDefault("dateTime") + ",1,10) = ?", dateTime);

        // convert LIst to array of drinks for adapter
        final Drink[] drinks2= drinks.toArray(new Drink[drinks.size()]);

        // Toast.makeText(this,"TODAY " + Integer.toString(drinks.size()) + " ", Toast.LENGTH_SHORT).show();
        ///total the drinks
        long lgTotal = 0;
        for (Drink d : drinks) {
            lgTotal = lgTotal + d.getAmount();
        }
        int iProgress = 0;
        iProgress = (int) lgTotal;
        //Toast.makeText(this,"Progress " + Integer.toString(iProgress) + " ", Toast.LENGTH_SHORT).show();
        //pbTarget.setProgress(iProgress);
        // pbTarget.setCurrent(iProgress,"h2o");
        pb.setProgress(iProgress);
        tvTotal.setText(String.valueOf(iProgress));
        //pbTarget.refreshDrawableState();



        // set up gridview
        GridView gridView = (GridView)findViewById(R.id.gridview);
        final DrinkAdapter drinkAdapater = new DrinkAdapter(this, drinks2);
        gridView.setAdapter(drinkAdapater);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Drink drink= drinks2[position];
                drink.doSomething();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                drinkAdapater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void loadBeverages() {
        List<Beverage> bevs = Beverage.listAll(Beverage.class);

        //clear them out
         //Beverage.deleteAll(Beverage.class);
        if (bevs.size() == 0) {
            //public Beverage(String bevID, String bevName, int bevAmount, String bevUnit ){
            Beverage b = new Beverage("8 oz", 8, "oz", "glass",1);
            b.save();
            b = new Beverage("12 oz", 12, "oz", "bottle_md",1);
            b.save();

            b = new Beverage("16 oz", 16, "oz", "bottle_md",1);
            b.save();


            b = new Beverage("22 oz", 22, "oz", "sports",1);
            b.save();


        }


    }


}
