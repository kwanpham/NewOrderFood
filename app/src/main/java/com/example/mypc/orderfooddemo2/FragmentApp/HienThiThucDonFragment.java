package com.example.mypc.orderfooddemo2.FragmentApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mypc.orderfooddemo2.Base.FragmentBase;
import com.example.mypc.orderfooddemo2.CustomAdapter.AdapterHienThiLoaiMonAnThucDon;
import com.example.mypc.orderfooddemo2.DAO.LoaiMonAnDAO;
import com.example.mypc.orderfooddemo2.DTO.LoaiMonAnDTO;
import com.example.mypc.orderfooddemo2.R;
import com.example.mypc.orderfooddemo2.ThemMonAnActivity;
import com.example.mypc.orderfooddemo2.TrangchuActivity;

import java.util.List;

/**
 * Created by MyPC on 14/12/2017.
 */

public class HienThiThucDonFragment extends Fragment {

    GridView gridView;
    AdapterHienThiLoaiMonAnThucDon adapter;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;

    int maban = 0;
    int maquyen;
    SharedPreferences sharedPreferences;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon, container, false);

        ((TrangchuActivity) getActivity()).getSupportActionBar().setTitle("Thực đơn");

        sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen", 0);

        if (maquyen == 1) {
            setHasOptionsMenu(true);
        }


        fragmentManager = getActivity().getSupportFragmentManager();

        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();


        gridView = view.findViewById(R.id.gvHienThiThucDon);
        adapter = new AdapterHienThiLoaiMonAnThucDon(getActivity(), loaiMonAnDTOList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Bundle bDuLieuThucDon = getArguments();
        if (bDuLieuThucDon != null) {
            maban = bDuLieuThucDon.getInt("maban");

        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maLoai = loaiMonAnDTOList.get(position).getMaLoai();

                HienThiDanhSachMonAnFragment hienThiDanhSachMonAnFragment = new HienThiDanhSachMonAnFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("maLoai", maLoai);
                bundle.putInt("maban", maban);
                Log.d("mabanthucdon", maban + "");
                hienThiDanhSachMonAnFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, hienThiDanhSachMonAnFragment);
                transaction.addToBackStack(hienThiDanhSachMonAnFragment.getClass().getName());

                transaction.commit();

            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        MenuItem itThemBanAn = menu.add(1, R.id.itThucDon, 1, "Thêm thực đơn");
        itThemBanAn.setIcon(R.drawable.logodangnhap);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); // thêm icon nết còn chỗ
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itThucDon:
                Intent iThemThucDon = new Intent(getActivity(), ThemMonAnActivity.class);
                startActivity(iThemThucDon);

                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adapter = new AdapterHienThiLoaiMonAnThucDon(getActivity(), loaiMonAnDTOList);
        gridView.setAdapter(adapter);
    }
}
