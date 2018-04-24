package com.example.mypc.orderfooddemo2.FragmentApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.mypc.orderfooddemo2.CustomAdapter.AdapterHienThiDanhSachMonAn;
import com.example.mypc.orderfooddemo2.DAO.MonAnDAO;
import com.example.mypc.orderfooddemo2.DTO.MonAnDTO;
import com.example.mypc.orderfooddemo2.R;

import java.util.List;

/**
 * Created by MyPC on 17/02/2018.
 */

public class HienThiDanhSachMonAnFragment extends Fragment{

    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    AdapterHienThiDanhSachMonAn adapterHienThiDanhSachMonAn;
    private int maban = 0;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon , container , false);

        gridView = view.findViewById(R.id.gvHienThiThucDon);
        gridView.setNumColumns(1);

        monAnDAO = new MonAnDAO(getActivity());

        Bundle bundle = getArguments();
        if(bundle != null){
            int maLoai = bundle.getInt("maLoai");
            maban = bundle.getInt("maban");


            monAnDTOList = monAnDAO.LayDanhSachMonAnTheoLoai(maLoai);

            adapterHienThiDanhSachMonAn = new AdapterHienThiDanhSachMonAn(getActivity() , R.layout.custom_layout_hienthidanhsachmonan , monAnDTOList);
            gridView.setAdapter(adapterHienThiDanhSachMonAn);
        }



        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(event.getAction() == KeyEvent.ACTION_DOWN) { // Action_down = back
                    getFragmentManager().popBackStack();
                }
                return false;
            }
        });
        return view;
    }


}
