package com.example.mypc.orderfooddemo2.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.orderfooddemo2.DAO.LoaiMonAnDAO;

import com.example.mypc.orderfooddemo2.DTO.MonAnDTO;
import com.example.mypc.orderfooddemo2.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by MyPC on 27/03/2018.
 */

public class AdapterMonAnGoiMon extends BaseAdapter {
    private Context context;
    private List<MonAnDTO> monAnDTOList;
    ViewHolderMonAnGoiMon viewHolderMonAnGoiMon;
    LoaiMonAnDAO loaiMonAnDAO;

    public AdapterMonAnGoiMon(Context context, List<MonAnDTO> data) {
        this.context = context;
        this.monAnDTOList = data;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return monAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return monAnDTOList.get(i).getMaLoai();
    }

    public class ViewHolderMonAnGoiMon {

        TextView tvTenMonAn , tvSoLuongTungMon , tvGiaTienMonAn;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            viewHolderMonAnGoiMon = new ViewHolderMonAnGoiMon();
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_danh_sach_mon_an_them_goi_mon, viewGroup, false);


            viewHolderMonAnGoiMon.tvTenMonAn = (TextView) view.findViewById(R.id.tvTenMon);
            viewHolderMonAnGoiMon.tvSoLuongTungMon = view.findViewById(R.id.tvSoLuongTungMon);
            viewHolderMonAnGoiMon.tvGiaTienMonAn = view.findViewById(R.id.tvGiaTienMonAn);

            view.setTag(viewHolderMonAnGoiMon);
        } else {
            viewHolderMonAnGoiMon = (ViewHolderMonAnGoiMon) view.getTag();
        }

        MonAnDTO monAnDTO = monAnDTOList.get(i);
        int maloai = monAnDTO.getMaLoai();


        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        viewHolderMonAnGoiMon.tvTenMonAn.setText(monAnDTO.getTenMonAn());
        viewHolderMonAnGoiMon.tvGiaTienMonAn.setText(formatter.format(monAnDTO.getGiaTien()));


        return view;


    }
}
