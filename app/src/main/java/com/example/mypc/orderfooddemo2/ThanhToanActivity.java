package com.example.mypc.orderfooddemo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.orderfooddemo2.CustomAdapter.AdapterHienThiThanhToan;
import com.example.mypc.orderfooddemo2.DAO.GoiMonDAO;
import com.example.mypc.orderfooddemo2.DTO.ThanhToanDTO;
import com.example.mypc.orderfooddemo2.Utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by MyPC on 21/02/2018.
 */

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {

    GridView gridView;
    Button btnThanhToan, btnThoat;
    TextView tvTongTien;
    EditText edGiamGia;
    GoiMonDAO goiMonDAO;

    AdapterHienThiThanhToan adapterHienThiThanhToan;
    List<ThanhToanDTO> thanhToanDTOList;
    private long tongtien = 0;
    private int magoimon = 0;
    private int giamGia;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanh_toan);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (GridView) findViewById(R.id.gvThanhToan);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        btnThoat = (Button) findViewById(R.id.btnThoatThanhToan);
        tvTongTien = (TextView) findViewById(R.id.tvTongTien);

        edGiamGia = (EditText) findViewById(R.id.edGiamGia);

        edGiamGia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                giamGia = (s.length()==0) ? 0 : Integer.valueOf(s.toString()) ;
                if(giamGia<tongtien){
                    tvTongTien.setText(getResources().getString(R.string.tongcong) + " " + (tongtien-giamGia));
                } else {
                    tvTongTien.setText(getResources().getString(R.string.tongcong) + " " + 0);
                }
            }
        });

        goiMonDAO = new GoiMonDAO(this);


        magoimon = getIntent().getIntExtra("magoimon", 0); // gán mặc đinh mã ban = 0
        if (magoimon != 0) {

            hienThiThanhToan();

            for (int i = 0; i < thanhToanDTOList.size(); i++) {
                int soluong = thanhToanDTOList.get(i).getSoLuong();
                int giatien = thanhToanDTOList.get(i).getGiaTien();

                tongtien += (soluong * giatien);
            }


            tvTongTien.setText(getResources().getString(R.string.tongcong) + " " + Utils.tienVietFormat(tongtien)+" VNĐ");

            btnThanhToan.setOnClickListener(this);
            btnThoat.setOnClickListener(this);
        }
    }

    private void hienThiThanhToan() {
        thanhToanDTOList = goiMonDAO.LayDanhSachMonAnTheoMaGoiMon(magoimon);

        adapterHienThiThanhToan = new AdapterHienThiThanhToan(this, thanhToanDTOList);
        gridView.setAdapter(adapterHienThiThanhToan);
        adapterHienThiThanhToan.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThanhToan:

                boolean kiemTraGoiMon = goiMonDAO.CapNhatTrangThaiGoiMonTheoMaGoiMon(magoimon, "true");


                if (kiemTraGoiMon) {

                    Intent trangChuIntent = new Intent(this, TrangchuActivity.class);
                    startActivity(trangChuIntent);
                    finish();
                    Toast.makeText(this, "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Thanh Toán Lỗi !", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnThoatThanhToan:
                finish();
                break;
        }

    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return super.onNavigateUp();

    }
}
