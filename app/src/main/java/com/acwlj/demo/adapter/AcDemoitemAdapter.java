package com.acwlj.demo.adapter;


    import android.content.Context;
    import android.content.Intent;
    import android.support.v7.widget.RecyclerView;
    import android.text.TextUtils;
    import android.view.Gravity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;

    import butterknife.Bind;
    import butterknife.ButterKnife;
//    import butterknife.InjectView;
    import com.acwlj.demo.R;
    import com.acwlj.demo.ui.main.MainActivity;
    import com.acwlj.demo.uiActivity.CatLoadingActivity;
    import com.acwlj.demo.uiActivity.CircularRevealActivity;
    import com.acwlj.demo.uiActivity.ClipActivity;
    import com.acwlj.demo.uiActivity.CreditCardActivity;
    import com.acwlj.demo.uiActivity.MoveActivity;
    import com.acwlj.demo.uiActivity.NormalActivity;
    import com.acwlj.demo.uiActivity.QihooActivity;
    import com.acwlj.demo.uiActivity.StatusBarActivity;
    import com.acwlj.demo.util.L;

    /**
     * Created by lj on 2016/4/13.
     */
    public class AcDemoitemAdapter extends RecyclerView.Adapter<AcDemoitemAdapter.AcItemviewHolder> {
        private static final int TYPE_ITEM_LEFT = 0;
        private static final int TYPE_ITEM_RIGHT = 1;
        private static final int TYPE_HEADER = 2;
private Context context;
        private List<String> data;
        private View mHeaderView;

        public AcDemoitemAdapter(Context context, View headerView) {
            this.context=context;
            mHeaderView = headerView;
            data = new ArrayList<>();
            data.add(String.valueOf("ui.main.MainActivity"));
            data.add(".uiActivity.StatusBar");
            data.add(".uiActivity.CreditCard");
            data.add("CircualRevealSample");
            data.add("CatLoadingView");
            data.add("MenuTabs");//系统自带
            data.add("MenuClip");
            data.add("MenuRipple");//360波纹菜单
            data.add("MenuMove");//
        }

        public void onPullDownAddHeader(int count) {
            for (int i = 0; i < count; i++) {
                data.add(0, String.format("new向下 %d",i));
            }
        }

        public void onPullUpAdd(int count) {
            for (int i = 0; i < count; i++) {
                data.add(String.format("more向上 %d",i));
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                return ((position - 1) % 2 == 0) ? TYPE_ITEM_LEFT : TYPE_ITEM_RIGHT;
            }
        }

        @Override
        public AcItemviewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            if (viewType == TYPE_HEADER) {
                return new HeaderViewHolder(mHeaderView);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.numbered_item, parent, false);
                TextViewHolder holder = new TextViewHolder(view);
                switch (viewType) {
                    default:
                    case TYPE_ITEM_LEFT:
                        holder.textView.setGravity(Gravity.START);
                        return holder;
                    case TYPE_ITEM_RIGHT:
                        holder.textView.setGravity(Gravity.END);
                        return holder;
                }
            }
        }

        @Override
        public void onBindViewHolder(AcItemviewHolder holder, final int position) {

            switch (holder.getItemViewType()) {
                case TYPE_ITEM_LEFT:
                case TYPE_ITEM_RIGHT:
                    TextViewHolder textViewHolder = (TextViewHolder) holder;
                    textViewHolder.textView.setText(data.get(position - 1));
                    textViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "vid=" + v.getId() + position, Toast.LENGTH_SHORT).show();
                            if(TextUtils.equals(data.get(position - 1),".uiActivity.StatusBar")){
                                context.startActivity(new Intent(context, StatusBarActivity.class));
                            }else if (TextUtils.equals(".uiActivity.CreditCard",data.get(position - 1))){
                                context.startActivity(new Intent(context, CreditCardActivity.class));
                            }else if (TextUtils.equals("CircualRevealSample",data.get(position-1))){
                                context.startActivity(new Intent(context, CircularRevealActivity.class));
                            }else if (TextUtils.equals("CatLoadingView",data.get(position-1))){
                                 context.startActivity(new Intent(context,CatLoadingActivity.class));
                            }else if (TextUtils.equals("MenuTabs",data.get(position-1))){
                                context.startActivity(new Intent(context,NormalActivity.class));//系统自带
                            }else if (TextUtils.equals("MenuClip",data.get(position-1))){
                                context.startActivity(new Intent(context,ClipActivity.class));//上移
                            }else if (TextUtils.equals("MenuRipple",data.get(position-1))){
                                context.startActivity(new Intent(context,QihooActivity.class));//360波纹菜单
                            }else if (TextUtils.equals("MenuMove",data.get(position-1))){
                                context.startActivity(new Intent(context,MoveActivity.class));//动画展开菜单
                            }else{
                                context.startActivity(new Intent(context, MainActivity.class));
                            }
                        }
                    });
                case TYPE_HEADER:
                default:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return data != null ? data.size() + 1 : 0;
        }

        @Override
        public void onViewRecycled(AcItemviewHolder holder) {
            L.d("adapter", "onViewRecycled");
            super.onViewRecycled(holder);
        }

        static class AcItemviewHolder extends RecyclerView.ViewHolder {

            public AcItemviewHolder(View itemView) {
                super(itemView);
            }
        }

        static class TextViewHolder extends AcItemviewHolder {
            @Bind(R.id.text)
            TextView textView;

            public TextViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        static class HeaderViewHolder extends AcItemviewHolder {

            public HeaderViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),"",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

}
