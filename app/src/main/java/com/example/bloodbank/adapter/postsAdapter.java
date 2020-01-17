package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.posts.postData;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.activity.BaseActivity;
import com.example.bloodbank.veiw.fragment.ActoinCycle.DetailsOfPostsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import static com.example.bloodbank.helper.HelperMethod.onLoadImageFromUrl;

public class postsAdapter extends RecyclerView.Adapter<postsAdapter.ViewHolder> {

    private boolean favourites;
    private Context context;
    private Activity activity;
    private List<postData> postDataList = new ArrayList<>();
    private BaseActivity baseActivity;

    public postsAdapter(Context context, Activity activity, BaseActivity baseActivity
            , List<postData> postDataList, boolean favourites) {
        this.context = context;
        this.activity = activity;
        this.postDataList = postDataList;
        this.baseActivity = baseActivity;
        this.favourites = favourites;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {

        holder.postItemTvText.setText(postDataList.get(position).getTitle());
        onLoadImageFromUrl(holder.postItemIvImage, postDataList.get(position).getThumbnailFullPath(), context);
        holder.postItemCbxFavorite.setChecked(postDataList.get(position).getIsFavourite());
    }

    private void setAction(final ViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailsOfPostsFragment detailsFragment = new DetailsOfPostsFragment();
                detailsFragment.post = postDataList.get(position);
                HelperMethod.replace(detailsFragment, baseActivity.getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);
            }
        });

        holder.postItemCbxFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favourites) {

                    postDataList.remove(position);
                    notifyDataSetChanged();
                }else {
                    postDataList.get(position).setIsFavourite(!postDataList.get(position).getIsFavourite());
                    holder.postItemCbxFavorite.setChecked(postDataList.get(position).getIsFavourite());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_item_tv_text)
        TextView postItemTvText;
        @BindView(R.id.post_item_cbx_favorite)
        CheckBox postItemCbxFavorite;
        @BindView(R.id.post_item_linear)
        LinearLayout postItemLinear;
        @BindView(R.id.post_item_iv_image)
        ImageView postItemIvImage;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
