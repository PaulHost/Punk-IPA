package ph.hostev.paul.punk_ipa.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    }
}
