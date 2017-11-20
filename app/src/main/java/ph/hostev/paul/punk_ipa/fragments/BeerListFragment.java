package ph.hostev.paul.punk_ipa.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.punk_ipa.App;
import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.adapters.BeerAdapter;
import ph.hostev.paul.punk_ipa.api.Callback;
import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.beans.SortParameters;
import ph.hostev.paul.punk_ipa.utils.NetworkUtil;

public class BeerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static BeerListFragment beerListFragment;
    private int mPagimation = 1;
    private SortParameters mSortParams = null;
    List<Beer> mBeerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    RangeSeekBar rangeABV;
    RangeSeekBar rangeIBU;
    RangeSeekBar rangeEBC;
    Button sortBtn;
    TextView openSortBtn;
    View layout;
    boolean isSortOpen = false;

    public static BeerListFragment newInstance() {
        if (beerListFragment == null) beerListFragment = new BeerListFragment();
        return beerListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_beer, container, false);
        layout = v.findViewById(R.id.sort_layout);
        openSortBtn = v.findViewById(R.id.open_sort_btn);
        openSortBtn.setOnClickListener(openCloseClickListener());
        rangeEBC = v.findViewById(R.id.range_ebc);
        rangeABV = v.findViewById(R.id.range_abv);
        rangeIBU = v.findViewById(R.id.range_ibu);
        sortBtn = v.findViewById(R.id.sort_btn);
        sortBtn.setOnClickListener(sortClickListener());
        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = v.findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(mBeerList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        load();
        return v;
    }

    private View.OnClickListener openCloseClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortOpen) {
                    openSortBtn.setText("V");
                    isSortOpen = false;
                    layout.setVisibility(View.GONE);
                } else {
                    openSortBtn.setText("X");
                    isSortOpen = true;
                    layout.setVisibility(View.VISIBLE);
                }
            }
        };
    }


    private void load() {
        if (NetworkUtil.isInternetAvailable(getActivity())) {
            if (mPagimation == 1) mBeerList.clear();
            recyclerView.addOnScrollListener(scrollListener());
            App.getAPI().get(mPagimation, mSortParams, new Callback<List<Beer>>() {
                @Override
                public void onSuccess(final List<Beer> beers) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            mBeerList.addAll(beers);
                            beerAdapter.notifyDataSetChanged();
                            mPagimation++;
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            });
        } else {
            try {
                mBeerList.clear();
                mBeerList.addAll(App.getDataBeer().queryForAll());
                beerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                mPagimation = 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    RecyclerView.OnScrollListener scrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.getItemCount() - 5 == layoutManager.findLastVisibleItemPosition()) {
                    load();
                }
            }
        };
    }

    private View.OnClickListener sortClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortParams = new SortParameters();
                mSortParams.setAbv_gt((Integer) rangeABV.getSelectedMaxValue());
                mSortParams.setEbc_gt((Integer) rangeEBC.getSelectedMaxValue());
                mSortParams.setIbu_gt((Integer) rangeIBU.getSelectedMaxValue());
                mSortParams.setAbv_lt((Integer) rangeABV.getSelectedMinValue());
                mSortParams.setEbc_lt((Integer) rangeEBC.getSelectedMinValue());
                mSortParams.setIbu_lt((Integer) rangeIBU.getSelectedMinValue());
                mPagimation = 1;
                load();
            }
        };
    }

    @Override
    public void onRefresh() {
        load();
    }
}
