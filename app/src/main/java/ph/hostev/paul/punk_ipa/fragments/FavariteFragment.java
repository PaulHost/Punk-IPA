package ph.hostev.paul.punk_ipa.fragments;

import android.os.Bundle;
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
import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.beans.Favorite;

public class FavariteFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static FavariteFragment favariteFragment;
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Beer> mBeerList = new ArrayList<>();

    public static FavariteFragment newInstance() {
        if (favariteFragment == null) favariteFragment = new FavariteFragment();
        return favariteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favarite, container, false);
        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = v.findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(mBeerList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        update();
        return v;
    }

    private void update() {
        mBeerList.clear();
        mBeerList.addAll(beers());
        beerAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private List<Beer> beers() {
        List<Beer> aBeerList = new ArrayList<>();
        Beer beer;

        try {
            List<Favorite> fragments = App.getFavoriteData().queryForAll();
            for (Favorite favorite : fragments) {
                beer = App.getDataBeer().queryForId(favorite.getId());
                aBeerList.add(beer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aBeerList;
    }

    @Override
    public void onRefresh() {
        update();
    }
}
