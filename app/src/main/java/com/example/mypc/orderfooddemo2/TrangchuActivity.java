package com.example.mypc.orderfooddemo2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mypc.orderfooddemo2.FragmentApp.HienThiGoiMonFragment;
import com.example.mypc.orderfooddemo2.FragmentApp.HienThiNhanVienFragment;
import com.example.mypc.orderfooddemo2.FragmentApp.HienThiThucDonFragment;
import com.example.mypc.orderfooddemo2.FragmentApp.ThongKeFragment;

public class TrangchuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tvTenNhanVien_Navigation;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);
        ViewHolder();


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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.itTrangChu:
                FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
                HienThiGoiMonFragment hienThiGoiMonFragment = new HienThiGoiMonFragment();
                transactionHienThiBanAn.replace(R.id.content, hienThiGoiMonFragment);
                transactionHienThiBanAn.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.itThucDon:
                FragmentTransaction transactionHienThiThucDon = fragmentManager.beginTransaction();
                HienThiThucDonFragment hienThiThucDonFragment = new HienThiThucDonFragment();
                transactionHienThiThucDon.setCustomAnimations(R.anim.hieuung_activity_vao, R.anim.hieuung_activity_ra);
                transactionHienThiThucDon.replace(R.id.content, hienThiThucDonFragment);
                transactionHienThiThucDon.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.itNhanVien:
                FragmentTransaction transactionNhanVien = fragmentManager.beginTransaction();
                HienThiNhanVienFragment hienThiNhanVienFragment = new HienThiNhanVienFragment();
                transactionNhanVien.replace(R.id.content, hienThiNhanVienFragment);
                transactionNhanVien.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.itThuChi:
                FragmentTransaction transactionThuChi = fragmentManager.beginTransaction();
                ThuChiFragment thuChiFragment = new ThuChiFragment();
                transactionThuChi.replace(R.id.content, thuChiFragment);
                transactionThuChi.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;

            case R.id.itThongKe:
                FragmentTransaction transactionThongKe = fragmentManager.beginTransaction();
                ThongKeFragment thongKeFragment = new ThongKeFragment();
                transactionThongKe.replace(R.id.content, thongKeFragment);
                transactionThongKe.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void ViewHolder() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
        };

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);


        tvTenNhanVien_Navigation = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvTenNhanVien_Navigation); // lay view header navition drawer
        String sTenDanhNhap = getIntent().getStringExtra("tendn");
        tvTenNhanVien_Navigation.setText(sTenDanhNhap);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
        HienThiGoiMonFragment hienThiGoiMonFragment = new HienThiGoiMonFragment();
        transactionHienThiBanAn.replace(R.id.content, hienThiGoiMonFragment);
        transactionHienThiBanAn.commit();
    }

}
