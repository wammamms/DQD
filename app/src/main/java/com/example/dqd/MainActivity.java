package com.example.dqd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.dqd.adapter.NewsAdapter;
import com.example.dqd.adapter.ViewPagerAdapter;
import com.example.dqd.fragment.DzFragment;
import com.example.dqd.fragment.HotFragment;
import com.example.dqd.fragment.TopFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //ViewPager
    private ViewPager mViewPager ;
    private ViewPagerAdapter mViewPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    //TabLayout
    private TabLayout mTabLayout;
    private Context mContext;
    private Locale EventBus;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //viewpager绑定
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        //fragment列表
        TopFragment recommendFragment =new TopFragment();
        HotFragment hotFragment = new HotFragment();
        DzFragment dzFragment = new DzFragment();
        mFragments.add(recommendFragment);
        mFragments.add(dzFragment);
        mFragments.add(hotFragment);
        //设置Adapter
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.setViewArrayList(mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);
        //设置TabLayout
        mTabLayout =findViewById(R.id.main_tabLayout);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            //获取不到View
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("---------------------->","onTabSelected");

//                String type = tab.getText().toString();
//                EventBus.getDefault().post(new LoginEvent(type));
//
                // 获取 tab 组件
//                View view = tab.getCustomView();
//                if(view == null) Log.d("---------------------->","view equals null!!");
//                if (null != view && view instanceof TextView) {
//                    // 改变 tab 选择状态下的字体大小
//                    ((TextView) view).setTextSize(22);
//                    // 改变 tab 选择状态下的字体颜色
//                    ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.black));
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                View view = tab.getCustomView();
//                if (null != view && view instanceof TextView) {
//                    Log.d("---------------------->", "onTabUnselected");
//                    // 改变 tab 未选择状态下的字体大小
//                    ((TextView) view).setTextSize(18);
//                    // 改变 tab 未选择状态下的字体颜色
//                    ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.white));
//                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }











//        for(int i=0;i< mTabLayout.getTabCount();i++){
//            TabLayout.Tab tab = mTabLayout.getTabAt(i);
//            if(tab != null) tab.setCustomView(getTabView(i));
//        }

//    /**
//     * 自定义Tab的View
//     * @param currentPosition
//     * @return
//     */
//    private View getTabView(int currentPosition) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tab,null);
//        //View view = (View) findViewById(R.layout.layout_tab);
//        TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
//        textView.setText(mViewPagerAdapter.getPageTitle(currentPosition));
//        return view;
//    }

}
