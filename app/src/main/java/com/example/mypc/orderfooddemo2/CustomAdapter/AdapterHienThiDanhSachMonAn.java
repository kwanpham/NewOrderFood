package com.example.mypc.orderfooddemo2.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mypc.orderfooddemo2.DTO.MonAnDTO;
import com.example.mypc.orderfooddemo2.R;

import java.util.List;

/**
 * Created by MyPC on 20/02/2018.
 */

public class AdapterHienThiDanhSachMonAn extends BaseAdapter {
    Context context;
    int layout;
    List<MonAnDTO> monAnDTOList;
    ViewHolderHienThiDanhSachMonAn viewHolderHienThiDanhSachMonAn;

    public AdapterHienThiDanhSachMonAn(Context context, int layout, List<MonAnDTO> monAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.monAnDTOList = monAnDTOList;
    }

    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return monAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monAnDTOList.get(position).getMaMonAn();
    }

    public class ViewHolderHienThiDanhSachMonAn {

        TextView txtTenMonAn, txtGiaTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderHienThiDanhSachMonAn = new ViewHolderHienThiDanhSachMonAn();
            view = inflater.inflate(layout, parent, false);


            viewHolderHienThiDanhSachMonAn.txtTenMonAn = (TextView) view.findViewById(R.id.tvTenDSMonAn);
            viewHolderHienThiDanhSachMonAn.txtGiaTien = (TextView) view.findViewById(R.id.tvGiaTienDSMonAn);

            view.setTag(viewHolderHienThiDanhSachMonAn);

        } else {
            viewHolderHienThiDanhSachMonAn = (ViewHolderHienThiDanhSachMonAn) view.getTag();
        }

        MonAnDTO monAnDTO = monAnDTOList.get(position);


        viewHolderHienThiDanhSachMonAn.txtTenMonAn.setText(monAnDTO.getTenMonAn());
        viewHolderHienThiDanhSachMonAn.txtGiaTien.setText("Giá " + monAnDTO.getGiaTien());

        return view;
    }
}
