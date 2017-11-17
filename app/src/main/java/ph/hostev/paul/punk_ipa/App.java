package ph.hostev.paul.punk_ipa;

import android.app.Application;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import ph.hostev.paul.punk_ipa.api.HTTPsClient;
import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.db.DatabaseHelper;

public class App extends Application {

    private static HTTPsClient httpsClient = null;
    private static DatabaseHelper helper = null;

    @Override
    public void onCreate() {
        super.onCreate();
        helper = new DatabaseHelper(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (helper != null) helper.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (helper != null) helper.close();
    }

    public static HTTPsClient getAPI() {
        if (httpsClient == null) httpsClient = new HTTPsClient();
        return httpsClient;
    }

    public static Dao<Beer, Integer> getDataBeer() throws SQLException {
        return helper.getBeerDao();
    }
}
