package com.example.mypc.orderfooddemo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mypc.orderfooddemo2.DAO.NhanVienDAO;
import com.example.mypc.orderfooddemo2.Utils.Utils;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDangNhapDN, btnDangKyDN;
    EditText edTenDangNhapDN, edMatKhauDN;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        nhanVienDAO = new NhanVienDAO(this);
        InitView();
        HienThiButtonDangNhap();
    }

    private void InitView() {
        btnDangKyDN = (Button) findViewById(R.id.btnDangKyDN);
        btnDangNhapDN = (Button) findViewById(R.id.btnDangNhapDN);
        edMatKhauDN = (EditText) findViewById(R.id.edMatKhauDN);
        edTenDangNhapDN = (EditText) findViewById(R.id.edTenDangNhapDN);

        btnDangNhapDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);
    }

    private void HienThiButtonDangNhap() {
        boolean check = nhanVienDAO.KiemTraNhanVien();
        if (check) {
            btnDangKyDN.setVisibility(View.GONE);      // ẨN ĐI VÀ AUTO BỊ THẾ CHỖ
            btnDangNhapDN.setVisibility(View.VISIBLE);  // HIỆN
        } else {
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDangNhapDN.setVisibility(View.GONE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButtonDangNhap();
    }

    private void btnDangNhap() {
        String sTenDangNhap = edTenDangNhapDN.getText().toString().trim();
        String sMatKhau = edMatKhauDN.getText().toString().trim();


        if (sTenDangNhap.equals("") || sMatKhau.equals("")) {
            Toast.makeText(DangNhapActivity.this, "Bạn không được để trống phần nào", Toast.LENGTH_LONG).show();
        } else {
            int kiemtra = nhanVienDAO.KiemTraDangNhap(sTenDangNhap, sMatKhau);
            if( kiemtra != 0){
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                int maquyen = nhanVienDAO.LayQuyenNhanVien(kiemtra);
                SharedPreferences sharedPreferences = getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("maquyen",maquyen);
                editor.commit();

                Utils.saveSharePrefrence("manv" , kiemtra+"" , this);
                Intent intentTrangChu = new Intent(DangNhapActivity.this , TrangchuActivity.class);
                intentTrangChu.putExtra("tendn" , sTenDangNhap);
                intentTrangChu.putExtra("manv" , kiemtra);
                startActivity(intentTrangChu);
                overridePendingTransition(R.anim.hieuung_activity_vao , R.anim.hieuung_activity_ra);
                finish();
            }else {
                Toast.makeText(this, "Sai tên đăng nhập hoặn mật khẩu", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void btnDangKy() {
        Intent intentDangKy = new Intent(DangNhapActivity.this , DangKyActivity.class);
        intentDangKy.putExtra("landautien", 1);
        startActivity(intentDangKy);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangNhapDN:
                btnDangNhap();
                break;
            case R.id.btnDangKyDN:
                btnDangKy();
                break;

        }

    }
}
