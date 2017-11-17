package ph.hostev.paul.punk_ipa.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.punk_ipa.App;
import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.adapters.BeerAdapter;
import ph.hostev.paul.punk_ipa.api.Callback;
import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.tools.NetworkTool;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private int pagimation = 1;
    List<Beer> beerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    NetworkTool networkTool;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkTool = new NetworkTool();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(beerList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        load();
    }

    private void load() {
        if (networkTool.isInternetAvailable(this)) {
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
