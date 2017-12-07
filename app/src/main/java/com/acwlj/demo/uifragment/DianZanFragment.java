package com.acwlj.demo.uifragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nooeyes.github.SmallBang;
import com.nooeyes.github.SmallBangListener;
import com.acwlj.demo.R;
import com.nooeyes.github.widget.PeriscopeLayout;
/**
 * Created by lj on 2016/4/14.
 * 对应library PeriscopeLayout=periscopelayout
 */
public class DianZanFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_dianzan_effect,container,false);

        this.initPageContent(layout);
        return layout;
    }

    private SmallBang mSmallBang;
    private ImageView mImage;
    private Button mButton;
    private TextView mText;

    private void initPageContent(View v){

        final PeriscopeLayout periscopeLayout = (PeriscopeLayout) v.findViewById(R.id.periscope);
        v.findViewById(R.id.dianzanBtn_periscope).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periscopeLayout.addHeart();
            }
        });
        mSmallBang = SmallBang.attach2Window(getActivity());

        mImage = (ImageView)v.findViewById(R.id.image);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like(v);
            }
        });


        mButton = (Button)v.findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(v);
            }
        });

        mText = (TextView)v.findViewById(R.id.text);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redText(v);
            }
        });
    }

    public void addNumber(View view){
        mSmallBang.bang(view,new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                toast("button +1");
            }
        });
    }

    public void redText(View view){
        mText.setTextColor(0xFFCD8BF8);
        mSmallBang.bang(view,50,new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                toast("text+1");
            }
        });
    }

    public void like(View view){
        mImage.setImageResource(R.drawable.heart_red);
        mSmallBang.bang(view);
        mSmallBang.setmListener(new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                toast("heart+1");
            }
        });
    }

    private void toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
