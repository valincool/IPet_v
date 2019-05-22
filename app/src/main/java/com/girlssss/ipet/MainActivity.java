package com.girlssss.ipet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.girlssss.ipet.Activity.Ask.Fragment.Fragment_c_Ask;
import com.girlssss.ipet.Activity.Home.Fragment.Fragment_a_Home;
import com.girlssss.ipet.Activity.Interact.Fragment.Fragment_b_Interact;
import com.girlssss.ipet.Activity.Me.Fragment.Fragment_d_Me;

public class MainActivity extends AppCompatActivity {
    private Fragment_a_Home f_a;
    private Fragment_b_Interact f_b;
    private Fragment_c_Ask f_c;
    private Fragment_d_Me f_d;
    private Fragment[] mfragments;
    private int mIndex;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }



    private void initView() {
        radioGroup=(RadioGroup)findViewById(R.id.menuGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for(int i=0;i<group.getChildCount();i++){
                    RadioButton rbtn=(RadioButton)group.getChildAt(i);

                    if(rbtn.isChecked()){
                        setIndexSelected(i);
                        break;
                    }
                }
            }
        });
    }

    private void initFragment() {
        f_a=new Fragment_a_Home();
        f_b=new Fragment_b_Interact();
        f_c=new Fragment_c_Ask();
        f_d=new Fragment_d_Me();

        mfragments=new Fragment[]{f_a,f_b,f_c,f_d};

        //开启事务
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();

        //添加首页
        //默认进入home页
        ft.add(R.id.main_fragment,f_a).commit();

        setIndexSelected(0);
    }

    private void setIndexSelected(int i) {
        if(mIndex==i){
            return;
        }

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();

        //隐藏
        ft.hide(mfragments[mIndex]);

        if(!mfragments[i].isAdded()){
            ft.add(R.id.main_fragment,mfragments[i]).show(mfragments[i]);
        }else {
            ft.show(mfragments[i]);
        }

        ft.commit();

        mIndex=i;
    }
}
