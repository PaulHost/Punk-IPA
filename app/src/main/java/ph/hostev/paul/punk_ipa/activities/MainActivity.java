package ph.hostev.paul.punk_ipa.activities;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private int pagimation = 1;
    List<Beer> beerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    NetworkTool networkTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkTool = new NetworkTool();
        recyclerView = findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(beerList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        if (networkTool.isInternetAvailable(this)) {
            recyclerView.addOnScrollListener(scrollListener());
            load();
        } else {
            fromDb();
        }
    }

    private void load() {
        App.getAPI().get(pagimation, null, new Callback<List<Beer>>() {
            @Override
            public void onSuccess(final List<Beer> beers) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        beerList.addAll(beers);
                        beerAdapter.notifyDataSetChanged();
                        pagimation++;
                    }
                });
            }
        });
    }

    private void fromDb() {
        try {
            beerList.addAll(App.getDataBeer().queryForAll());
            beerAdapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
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
}
