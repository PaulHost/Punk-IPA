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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.punk_ipa.App;
import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.adapters.BeerAdapter;
import ph.hostev.paul.punk_ipa.api.Callback;
import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.tools.NetworkTool;

public class BeerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private int pagimation = 1;
    private static BeerListFragment beerListFragment;
    List<Beer> beerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    NetworkTool networkTool;
    SwipeRefreshLayout swipeRefreshLayout;

    public static BeerListFragment newInstance() {
        if (beerListFragment == null) beerListFragment = new BeerListFragment();
        return beerListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_beer, container, false);
        networkTool = new NetworkTool();
        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = v.findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(beerList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        load();
        return v;
    }


    private void load() {
        if (networkTool.isInternetAvailable(getActivity())) {
            recyclerView.addOnScrollListener(scrollListener());
            App.getAPI().get(pagimation, null, new Callback<List<Beer>>() {
                @Override
                public void onSuccess(final List<Beer> beers) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            beerList.addAll(beers);
                            beerAdapter.notifyDataSetChanged();
                            pagimation++;
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            });
        } else {
            try {
                beerList.clear();
                beerList.addAll(App.getDataBeer().queryForAll());
                beerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        load();
    }
}
