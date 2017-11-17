package ph.hostev.paul.punk_ipa.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.sql.SQLException;

import ph.hostev.paul.punk_ipa.App;
import ph.hostev.paul.punk_ipa.R;
import ph.hostev.paul.punk_ipa.beans.Beer;

public class BeerActivity extends AppCompatActivity {

    private Beer beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        try {
            beer = App.getDataBeer().queryForId(getIntent().getIntExtra("id", 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(beer.getImageUrl())
                .into((ImageView) findViewById(R.id.beer_cover));

        ((TextView) findViewById(R.id.name)).setText(beer.getName());
        ((TextView) findViewById(R.id.tagline)).setText(beer.getTagline());
        ((TextView) findViewById(R.id.first_brewed)).setText(beer.getFirstBrewed());
        ((TextView) findViewById(R.id.abv)).setText(String.valueOf(beer.getAbv()));
        ((TextView) findViewById(R.id.ibu)).setText(String.valueOf(beer.getAbv()));
        ((TextView) findViewById(R.id.ebc)).setText(String.valueOf(beer.getEbc()));
        ((TextView) findViewById(R.id.description)).setText(beer.getDescription());

    }
}
