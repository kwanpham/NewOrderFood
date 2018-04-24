package com.example.mypc.orderfooddemo2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;


public class ThuChiFragment extends Fragment {

    TabHost tab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_plus_one, container, false);
        loadTabs(rootView);
        return rootView;
    }

    public void loadTabs(View view) {
        //Lấy Tabhost id ra trước (cái này của built - in android
        tab = (TabHost) view.findViewById(R.id.tabhost);
        //gọi lệnh setup
        tab.setup();
        TabHost.TabSpec spec;
        //Tạo tab1
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Thu");
        tab.addTab(spec);
        //Tạo tab2
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Chi");
        tab.addTab(spec);
        //Thiết lập tab mặc định được chọn ban đầu là tab 0
        tab.setCurrentTab(0);
        //Ở đây Tôi để sự kiện này để các bạn tùy xử lý
        //Ví dụ tab1 chưa nhập thông tin xong mà lại qua tab 2 thì báo...


        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                String s = "Tab tag =" + arg0 + "; index =" +
                        tab.getCurrentTab();
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
    }
}
