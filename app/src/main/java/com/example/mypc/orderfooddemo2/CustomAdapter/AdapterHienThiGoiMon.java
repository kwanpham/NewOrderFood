package com.example.mypc.orderfooddemo2.CustomAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.orderfooddemo2.DAO.GoiMonDAO;
import com.example.mypc.orderfooddemo2.DTO.GoiMonDTO;
import com.example.mypc.orderfooddemo2.DTO.ThanhToanDTO;
import com.example.mypc.orderfooddemo2.FragmentApp.HienThiGoiMonFragment;
import com.example.mypc.orderfooddemo2.R;
import com.example.mypc.orderfooddemo2.ThanhToanActivity;
import com.example.mypc.orderfooddemo2.ThemGoiMonActivity;
import com.example.mypc.orderfooddemo2.TrangchuActivity;
import com.example.mypc.orderfooddemo2.Utils.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MyPC on 13/12/2017.
 */

public class AdapterHienThiGoiMon extends RecyclerView.Adapter<AdapterHienThiGoiMon.RecyclerViewHolder> {

    private Context context;
    private List<GoiMonDTO> goiMonDTOList;
    private GoiMonDAO goiMonDAO;
    private FragmentManager fragmentManager;
    int tongTien, tongSoLuong;

    public AdapterHienThiGoiMon(Context context, List<GoiMonDTO> goiMonDTOList) {
        this.context = context;
        this.goiMonDTOList = goiMonDTOList;
        goiMonDAO = new GoiMonDAO(context);
        fragmentManager = ((TrangchuActivity) context).getSupportFragmentManager();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_hienthigoimon, parent, false);
        return new AdapterHienThiGoiMon.RecyclerViewHolder(rootView);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final GoiMonDTO temp = goiMonDTOList.get(position);

        final List<ThanhToanDTO> thanhToanDTOList = goiMonDAO.LayDanhSachMonAnTheoMaGoiMon(temp.getMaGoiMon());


        tongSoLuong = 0;
        tongTien = 0;
        for (int i = 0; i < thanhToanDTOList.size(); i++) {
            int soluong = thanhToanDTOList.get(i).getSoLuong();
            int giatien = thanhToanDTOList.get(i).getGiaTien();
            tongSoLuong = tongSoLuong + soluong;
            tongTien += (soluong * giatien); // tongtien = tongtien + (soluong*giatien)
        }

        holder.tvTongTienBanAn.setText(Utils.tienVietFormat(tongTien) + "");
        holder.tvSoLuong.setText(tongSoLuong + "");


        if (temp.getMaBan() == 0) {
            holder.tvMaBan.setText("");
            holder.tvMaBan.setBackgroundColor(Color.parseColor("#A6A6A6"));
        } else {
            holder.tvMaBan.setText(temp.getMaBan() + "");
        }

        if (temp.getSoNguoi() == 0) {
            holder.tvSoNguoi.setVisibility(View.INVISIBLE);
        } else {
            holder.tvSoNguoi.setText(temp.getSoNguoi() + "");
        }


        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iThanhToan = new Intent(context, ThanhToanActivity.class);
                iThanhToan.putExtra("magoimon", temp.getMaGoiMon());

                context.startActivity(iThanhToan);
            }
        });

        holder.btnHuyGoiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(temp.getMaGoiMon());
            }
        });

        holder.rlChiTietGoiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThemGoiMonAcitivity = new Intent(context, ThemGoiMonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thanhToanDTOList" , (Serializable) thanhToanDTOList);
                bundle.putInt("magoimon" , temp.getMaGoiMon());
                intentThemGoiMonAcitivity.putExtras(bundle);
                context.startActivity(intentThemGoiMonAcitivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goiMonDTOList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvTongTienBanAn, tvSoLuong, tvSoNguoi, tvMaBan;
        Button btnHuyGoiMon, btnThanhToan;
        RelativeLayout rlChiTietGoiMon;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvTongTienBanAn = itemView.findViewById(R.id.tvTongTienBanAn);
            tvSoNguoi = itemView.findViewById(R.id.tvSoNguoi);
            tvMaBan = itemView.findViewById(R.id.tvMaBan);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuongGoiMon);

            btnHuyGoiMon = itemView.findViewById(R.id.btnHuyGoiMon);
            btnThanhToan = itemView.findViewById(R.id.btnThuTien);

            rlChiTietGoiMon = itemView.findViewById(R.id.rlChiTietGoiMon);

        }
    }

    private void showAlertDialog(final int maGoiMon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh Báo !");
        builder.setMessage("Bạn có muốn hủy các món đã gọi không ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean kiemtra = goiMonDAO.XoaGoiMon(maGoiMon);
                if (kiemtra) {
                    Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
                    HienThiGoiMonFragment hienThiGoiMonFragment = new HienThiGoiMonFragment();
                    transactionHienThiBanAn.replace(R.id.content, hienThiGoiMonFragment);
                    transactionHienThiBanAn.commit();
                } else {
                    Toast.makeText(context, "Lỗi !", Toast.LENGTH_SHORT).show();
                }


            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}

