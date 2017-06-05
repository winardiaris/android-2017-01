package xyz.winardiaris.android.mypocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import xyz.winardiaris.android.mypocket.dao.DataDao;
import xyz.winardiaris.android.mypocket.dao.DataDao.*;
import xyz.winardiaris.android.mypocket.dao.DataDbHelper;
import xyz.winardiaris.android.mypocket.domain.DataDomain;
import xyz.winardiaris.android.mypocket.fragment.AboutFragment;
import xyz.winardiaris.android.mypocket.fragment.ListDataFragment;
import xyz.winardiaris.android.mypocket.fragment.NewDataFragment;
import xyz.winardiaris.android.mypocket.fragment.SettingFragment;

public class AfterLoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton btAdd = (FloatingActionButton) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new NewDataFragment());
            }
        });
        Log.d("After login before dummy data", "------------------------");
        dummyData();

        DataDao dataDao = new DataDao(getBaseContext());
        int saldo = dataDao.getSaldo();
        toolbar.setTitle(NumberFormat.getCurrencyInstance(new Locale("id","id")).format(saldo));

        loadFragment(new ListDataFragment());
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
        getMenuInflater().inflate(R.menu.after_login, menu);
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
            loadFragment(new SettingFragment());
        } else if(id == R.id.action_refresh){
            //refresh
            Log.d("refresh","true");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list_data) {
            loadFragment(new ListDataFragment());
        } else if (id == R.id.nav_logout){

            Toast.makeText(AfterLoginActivity.this,"Logout",Toast.LENGTH_SHORT).show();

            Intent beforeLoginActivity = new Intent(this.getBaseContext(),BeforeLoginActivity.class);
            startActivity(beforeLoginActivity);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {
            loadFragment(new AboutFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadFragment(Fragment fr){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_after_login,fr);
        fragmentTransaction.commit();
    }

    private void dummyData(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        DataDomain d0 = new DataDomain();
        d0.setValue(new BigDecimal("0"));
        d0.setType("D");
        try {
            d0.setDate(format.parse("1993-02-02"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d0.setDescription("null");
        d0.setUserId(1);
        d0.setReceiptImage("aaa.jpg");

        DataDomain d1 = new DataDomain();
        d1.setValue(new BigDecimal("9000"));
        d1.setType("C");
        try {
            d1.setDate(format.parse("2017-02-20"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d1.setDescription("beli micin");
        d1.setUserId(1);
        d1.setReceiptImage("aaa.jpg");

        DataDomain d2 = new DataDomain();
        d2.setValue(new BigDecimal("900000"));
        d2.setType("D");
        try {
            d2.setDate(format.parse("2017-02-15"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d2.setDescription("hadiah vocer gosok");
        d2.setUserId(1);
        d2.setReceiptImage("aaa.jpg");

        DataDomain d3 = new DataDomain();
        d3.setValue(new BigDecimal("50000"));
        d3.setType("C");
        try {
            d3.setDate(format.parse("2017-06-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d3.setDescription("Beli rinso");
        d3.setUserId(1);
        d3.setReceiptImage("aaa.jpg");


        Log.d("Dummy Data 1" , d1.toString());
        DataDao dataDao = new DataDao(this);
//        dataDao.emptyDb();
//        dataDao.insertData(d0);

    }

}
