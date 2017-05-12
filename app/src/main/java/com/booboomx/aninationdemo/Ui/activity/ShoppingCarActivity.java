package com.booboomx.aninationdemo.Ui.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.booboomx.aninationdemo.R;
import com.booboomx.aninationdemo.Ui.adapter.ShoppingCarAdapter;
import com.booboomx.aninationdemo.widget.BezierPointView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 模仿外卖之类app添加购物车的动画
 */
public class ShoppingCarActivity extends AppCompatActivity implements ShoppingCarAdapter.OnItemClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.btn)
    AppCompatButton mBtn;

    private ShoppingCarAdapter mAdapter;
    private ArrayList<String> mStrings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_car);
        ButterKnife.bind(this);


        initData();
        init();
    }

    private void initData() {

        for (int i = 0; i < 20; i++) {
            mStrings.add(i + "-->love" + '\r' + "Android");
        }


    }

    private void init() {

        mAdapter=new ShoppingCarAdapter(this,mStrings);
        mRecyclerView.setAdapter(mAdapter );


        mAdapter.setListener(this);

    }

    @Override
    public void onItemClick(View view, int p) {

        BezierPointView bezierPointView = new BezierPointView(this);
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        rootView.addView(bezierPointView);


        int position[] = new int[2];
        int endPosition[] = new int[2];

        view.getLocationInWindow(position);
        mBtn.getLocationInWindow(endPosition);

         bezierPointView.setStartPosition(new Point(position[0],position[1]));
         bezierPointView.setEndPosition(new Point(endPosition[0],endPosition[1]));
        bezierPointView.startBeizerAnimation();
    }
}
