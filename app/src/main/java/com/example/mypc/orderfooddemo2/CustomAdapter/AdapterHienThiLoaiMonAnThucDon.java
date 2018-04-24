package com.example.mypc.orderfooddemo2.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.orderfooddemo2.DAO.LoaiMonAnDAO;
import com.example.mypc.orderfooddemo2.DTO.LoaiMonAnDTO;
import com.example.mypc.orderfooddemo2.R;

import java.util.List;

/**
 * Created by MyPC on 20/12/2017.
 */

public class AdapterHienThiLoaiMonAnThucDon extends BaseAdapter {

    private Context context;
    private List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHolderHienThiLoaiThucDon viewHolderHienThiLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;

    public AdapterHienThiLoaiMonAnThucDon(Context context, List<LoaiMonAnDTO> loaiMonAnDTOList) {
        this.context = context;
        this.loaiMonAnDTOList = loaiMonAnDTOList;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return loaiMonAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaiMonAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return loaiMonAnDTOList.get(i).getMaLoai();
    }

    public class ViewHolderHienThiLoaiThucDon {

        TextView tvTenLoaiThucDon;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            viewHolderHienThiLoaiThucDon = new ViewHolderHienThiLoaiThucDon();
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_hienloaimonan, viewGroup, false);


            viewHolderHienThiLoaiThucDon.tvTenLoaiThucDon = (TextView) view.findViewById(R.id.tvTenLoaiThucDon);

            view.setTag(viewHolderHienThiLoaiThucDon);
        } else {
            viewHolderHienThiLoaiThucDon = (ViewHolderHienThiLoaiThucDon) view.getTag();
        }

        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(i);
        int maloai = loaiMonAnDTO.getMaLoai();



        viewHolderHienThiLoaiThucDon.tvTenLoaiThucDon.setText(loaiMonAnDTO.getTenLoai());



        return view;




    }
}
