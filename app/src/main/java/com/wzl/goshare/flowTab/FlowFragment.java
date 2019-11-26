package com.wzl.goshare.flowTab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wzl.goshare.R;

/**
 * 作者：Create on 2019/9/13 02:37  by  wzl
 * 描述：
 * 最近修改：2019/9/13 02:37 modify by wzl
 */
public class FlowFragment extends Fragment{
    private String mNames[] = {
                     "welcome","android","TextView",
                      "apple","jamy","kobe bryant",
                       "jordan","layout","viewgroup",
                      "margin","padding","text",
                     "name","type","search","logcat"
             };
    private FlowLayout mFlowLayout;

    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.flowlayout, container,false);
        mContext=this.getActivity();
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFlowLayout = (FlowLayout) view.findViewById(R.id.flowlayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView textview = new TextView(getActivity());
            textview.setText(mNames[i]);
            Log.i("TTTT",textview.getText().toString());
            textview.setTextColor(Color.BLACK);
            textview.setBackground(getResources().getDrawable(R.drawable.textstyle));
            mFlowLayout.addView(textview,lp);
            mFlowLayout.setVisibility(View.VISIBLE);
            Log.i("TTTT",mFlowLayout.toString());
        }
    }





}
