package ph.hostev.paul.punk_ipa.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.punk_ipa.App;
import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.adapters.BeerAdapter;
import ph.hostev.paul.punk_ipa.api.Callback;
import ph.hostev.paul.punk_ipa.beans.Beer;

public class MainActivity extends AppCompatActivity {

    List<Beer> beerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(beerList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        App.getAPI().get(1, null, new Callback<List<Beer>>() {
            @Override
            public void onSuccess(final List<Beer> beers) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        beerList.addAll(beers);
                        beerAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
