package com.example.mypc.orderfooddemo2.FragmentApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypc.orderfooddemo2.CustomAdapter.AdapterHienThiGoiMon;
import com.example.mypc.orderfooddemo2.DAO.GoiMonDAO;
import com.example.mypc.orderfooddemo2.DTO.GoiMonDTO;
import com.example.mypc.orderfooddemo2.DTO.ThanhToanDTO;
import com.example.mypc.orderfooddemo2.Database.CreateDatabase;
import com.example.mypc.orderfooddemo2.R;
import com.example.mypc.orderfooddemo2.ThemGoiMonActivity;
import com.example.mypc.orderfooddemo2.TrangchuActivity;

import java.util.List;

/**
 * Created by MyPC on 22/11/2017.
 */

public class HienThiGoiMonFragment extends Fragment {

    public final static int RESQUEST_CODE_THEM = 111;

    private static HienThiGoiMonFragment intance;
    RecyclerView rvHienThiGoiMon;
    GoiMonDAO goiMonDAO;
    List<GoiMonDTO> goiMonDAOList;
    List<ThanhToanDTO> thanhToanDTOList;
    AdapterHienThiGoiMon adapterHienThiGoiMon;
    SharedPreferences sharedPreferences;
    int maquyen;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthigoimon, container, false);

        goiMonDAO = new GoiMonDAO(getContext());



        rvHienThiGoiMon = view.findViewById(R.id.rvHienThiGoiMon);
        rvHienThiGoiMon.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));

        HienThiDanhSachBanAn();



        ((TrangchuActivity) getActivity()).getSupportActionBar().setTitle("Bàn Ăn");

        sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen", 0);

        Log.d("maquyen", "" + maquyen);


        if (maquyen == 1) {
            setHasOptionsMenu(true);
        }


        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maquyen != 0) {
            MenuItem itThemGoiMon = menu.add(1, R.id.itThemGoiMon, 1, "Thêm bàn ăn");
            MenuItem itXoaCSDL = menu.add(1, R.id.itXoaCSDL, 1, "Xoa CSDL");


            itThemGoiMon.setIcon(R.drawable.thembanan);
            itThemGoiMon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); // Hien thi icon

            itXoaCSDL.setIcon(R.drawable.password);
            itXoaCSDL.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itThemGoiMon:
                Bundle bundle = new Bundle();
                Intent intentThemGoiMonActivity = new Intent(getContext(), ThemGoiMonActivity.class);
                startActivity(intentThemGoiMonActivity);
                break;

            case R.id.itXoaCSDL:
                CreateDatabase createDatabase = new CreateDatabase(getContext());
                createDatabase.xoaDataBase(getContext());
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    private void HienThiDanhSachBanAn() {
        goiMonDAOList = goiMonDAO.LayDanhSachGoiMonChuaTraTien();
        adapterHienThiGoiMon = new AdapterHienThiGoiMon(getContext(), goiMonDAOList);
        rvHienThiGoiMon.setAdapter(adapterHienThiGoiMon);
    }



    @Override
    public void onResume() {
        super.onResume();
        HienThiDanhSachBanAn();
    }


}
