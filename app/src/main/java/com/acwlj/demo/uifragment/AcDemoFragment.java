package com.acwlj.demo.uifragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.acwlj.demo.R;
import com.acwlj.demo.adapter.AcDemoitemAdapter;
import com.acwlj.demo.util.L;
import com.acwlj.demo.util.ScrollUtils;
import com.acwlj.demo.view.DRecyclerView;
import com.acwlj.demo.uiActivity.AcDemoActivity.AcDemoSetToolbar;
/**
 * Created by lj on 2016/4/13.
 */
public class AcDemoFragment extends Fragment{
    DRecyclerView drecyclerView;
    View headerView;
    AcDemoitemAdapter adapter;

    private int mHeaderHeight;
    private AcDemoSetToolbar setActivityToolbar;
    public void setAcDemoToolbar(AcDemoSetToolbar setToolbar){
        setActivityToolbar=setToolbar;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_acdemo, container, false);

        this.initPageContent(layout);
        return layout;
    }
    private void initPageContent(View v){
        drecyclerView=(DRecyclerView)v.findViewById(R.id.fmacd_dRecycler);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_scroll_header, null);
        adapter = new AcDemoitemAdapter(getActivity(), headerView);

        //每行列数
        drecyclerView.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? drecyclerView.getSpanCount() : 1;
            }
        });

        drecyclerView.setColorSchemeResources(android.R.color.holo_blue_light);
        drecyclerView.setAutoLoadMore(false);
        drecyclerView.setOnLoadListener(new DRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                delayAction(new Runnable() {
                    @Override
                    public void run() {
                        adapter.onPullDownAddHeader(1);
                        adapter.notifyDataSetChanged();
                        drecyclerView.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onLoadMore() {
                delayAction(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter.getItemCount() < 50) {
                            drecyclerView.setLoading(false);
                            adapter.onPullUpAdd(2);
                            adapter.notifyDataSetChanged();
//                            adapter.notifyItemRangeChanged(adapter.getItemCount() - 2, 2);
                        } else {
                            drecyclerView.promptEnd();
                        }
                    }
                });
            }
        });
        drecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollY;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (drecyclerView.getLayoutManager().findFirstCompletelyVisibleItemPosition() == 0) {
                    scrollY = 0;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollY += dy;
                L.d("FragmentMain", "onScrolled dx=%d dy=%d scrollY=%d", dx, dy, scrollY);
                float alpha = (float) scrollY / mHeaderHeight;
                int color = ScrollUtils.getColorWithAlpha(alpha, getResources().getColor(R.color.primary));
                if (setActivityToolbar!=null){
                    setActivityToolbar.onSetToolbar(color);
                }
                headerView.setTranslationY(scrollY / 2);
            }
        });

        drecyclerView.setAdapter(adapter);
        drecyclerView.promptLoading();
        drecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.onPullUpAdd(1);
                adapter.notifyDataSetChanged();

                drecyclerView.setLoadMoreEnable(true);
            }
        }, 2000);
    }

    private void delayAction(Runnable action) {
        drecyclerView.postDelayed(action, 1500);
    }

}