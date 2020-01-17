package com.example.bloodbank.veiw.fragment.HomeCycle.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.adapter.postsAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.posts.Posts;
import com.example.bloodbank.data.model.posts.postData;
import com.example.bloodbank.helper.OnEndLess;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.GeneralRequests.getSpinnerData;


public class PostsFragment extends BaseFragment {

    @BindView(R.id.postes_fragment_rv_get_postes)
    RecyclerView postesFragmentRvGetPostes;
    @BindView(R.id.fragment_postes_sp_get_all_postes_filter)
    Spinner fragmentPostesSpGetAllPostesFilter;
    @BindView(R.id.fragment_postes_sp_filter)
    EditText fragmentPostesSpFilter;
    @BindView(R.id.fragment_postes_ll_filter_container)
    LinearLayout fragmentPostesLlFilterContainer;
    Unbinder unbinder;

    private ApiService apiService;
    private LinearLayoutManager linearLayoutManager;

    private List<postData> postDatalist = new ArrayList<>();
    private postsAdapter postsAdapter;
    private Integer maxPage = 1;
    private OnEndLess onEndLess;
    private int previous_page;
    private boolean FILTER = false;

    public boolean Favourites = false;

    private SpinnerAdapter spinnerAdapter;


    public PostsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_postes, container, false);
        unbinder = ButterKnife.bind(this, view);


        apiService = getClient();

        initRecyclerView();

        if (Favourites) {
            fragmentPostesLlFilterContainer.setVisibility(View.GONE);
        }else {
            spinnerAdapter = new SpinnerAdapter(getActivity());
            getSpinnerData(apiService.getCategories(), fragmentPostesSpGetAllPostesFilter
                    , spinnerAdapter, "saving");
        }

        return view;
    }

    private void initRecyclerView() {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        postesFragmentRvGetPostes.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (maxPage != 0) {
                    if (current_page <= maxPage) {
                        if (maxPage != 0 && current_page != 1) {
                            previous_page = current_page;
                            if (Favourites) {
                                getFavourites(current_page);
                            }else {
                                if (FILTER) {
                                    getPostFilter(current_page);
                                } else {
                                    getPost(current_page);
                                }
                            }
                        } else {
                            onEndLess.current_page = previous_page;
                        }
                    }
                }
            }
        };

        postesFragmentRvGetPostes.addOnScrollListener(onEndLess);
        postsAdapter = new postsAdapter(getActivity(), getActivity(), baseActivity, postDatalist,Favourites);
        postesFragmentRvGetPostes.setAdapter(postsAdapter);

        if (Favourites) {
            getFavourites(1);
        }else {
            getPost(1);
        }

    }

    private void getFavourites(int page) {
        Call<Posts> call = apiService.getMyFavourites("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", page);

        onCall(page, call);
    }

    private void getPost(int page) {

        Call<Posts> call = apiService.getPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", page);

        onCall(page, call);
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

    @OnClick(R.id.fragment_postes_i_btn_filter)
    public void onViewClicked() {

        postDatalist = new ArrayList<>();
        postsAdapter = new postsAdapter(getActivity(), getActivity(), baseActivity, postDatalist, Favourites);
        previous_page = 1;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 0;

        getPostFilter(1);

    }


    private void getPostFilter(int page) {

        Call<Posts> call = apiService.getPostsFilter("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27"
                , 1, "information", spinnerAdapter.selectedId);

        onCall(page, call);

    }

    private void onCall(final int page, Call<Posts> call) {
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                try {

                    if (response.body().getStatus() == 1) {

                        maxPage = response.body().getData().getLastPage();
                        postDatalist.addAll(response.body().getData().getData());
                        postsAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                Toast.makeText(baseActivity, "bbbbbbbbbbb", Toast.LENGTH_LONG).show();
            }
        });
    }

}
