package com.example.bloodbank.veiw.fragment.HomeCycle.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.fragment.ActoinCycle.DonationOrderFragment;
import com.example.bloodbank.veiw.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_fragment_tl_tabs)
    TabLayout homeFragmentTlTabs;
    @BindView(R.id.home_fragment_vp_viewpager)
    ViewPager homeFragmentVpViewpager;
    Unbinder unbinder;
    private ViewPagerWithFragmentAdapter viewPagerWithFragmentAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        viewPagerWithFragmentAdapter = new ViewPagerWithFragmentAdapter(getActivity().getSupportFragmentManager());
        PostsFragment postsFragment = new PostsFragment();

        viewPagerWithFragmentAdapter.addPager(postsFragment, "posts");
        viewPagerWithFragmentAdapter.addPager(new DonationFragment(), "orders");
        homeFragmentVpViewpager.setAdapter(viewPagerWithFragmentAdapter);
        homeFragmentTlTabs.setupWithViewPager(homeFragmentVpViewpager);
        homeFragmentTlTabs.setSelectedTabIndicatorColor(R.color.colorPrimaryDark);

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

   @OnClick(R.id.fragment_home_btn)
    public void onViewClicked() {
        DonationOrderFragment donationOrderFragment=new DonationOrderFragment();
        HelperMethod.replace(donationOrderFragment,getActivity().getSupportFragmentManager(),R.id.home_cycle_fl_fragment_contaner,null,null);
    }
}
