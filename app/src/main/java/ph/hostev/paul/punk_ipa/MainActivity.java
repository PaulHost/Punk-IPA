package ph.hostev.paul.punk_ipa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ph.hostev.paul.punk_ipa.adapters.BeerAdapter;
import ph.hostev.paul.punk_ipa.api.modules.PunkModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<PunkModule> beerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(beerList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        App.getAPI().getBeerList(1).enqueue(new Callback<List<PunkModule>>() {
            @Override
            public void onResponse(Call<List<PunkModule>> call, Response<List<PunkModule>> response) {
                if (response.isSuccessful()) {

                    beerList.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<PunkModule>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
