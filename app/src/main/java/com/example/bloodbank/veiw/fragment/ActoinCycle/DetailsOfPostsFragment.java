package com.example.bloodbank.veiw.fragment.ActoinCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.postsAdapter;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.data.model.posts.postData;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.HelperMethod.onLoadImageFromUrl;


public class DetailsOfPostsFragment extends BaseFragment {


    @BindView(R.id.fragment_details_iv_image)
    ImageView fragmentDetailsIvImage;
    @BindView(R.id.fragment_details_tv_text)
    TextView fragmentDetailsTvText;
    @BindView(R.id.fragment_details_cbx_favorite)
    CheckBox fragmentDetailsCbxFavorite;
    @BindView(R.id.fragment_details_tv_details)
    TextView fragmentDetailsTvDetails;
    Unbinder unbinder;

    public postData post;

    public DetailsOfPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        onLoadImageFromUrl(fragmentDetailsIvImage, post.getThumbnailFullPath(), getActivity());
        fragmentDetailsTvText.setText(post.getTitle());
        fragmentDetailsCbxFavorite.setChecked(post.getIsFavourite());
        fragmentDetailsTvDetails.setText(post.getContent());

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
