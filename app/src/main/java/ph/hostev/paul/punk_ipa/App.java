package ph.hostev.paul.punk_ipa;

import android.app.Application;

import ph.hostev.paul.punk_ipa.api.APIClient;
import ph.hostev.paul.punk_ipa.api.PunkApi;

public class App extends Application {

    private static APIClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        client = APIClient.getInstance();
    }

    public static PunkApi getAPI() {
        return client.getPunkApi();
    }
}
