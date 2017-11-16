package ph.hostev.paul.punk_ipa;

import android.app.Application;

import ph.hostev.paul.punk_ipa.api.HTTPsClient;

public class App extends Application {

    private static HTTPsClient httpsClient;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static HTTPsClient getAPI() {
        if (httpsClient == null) httpsClient = new HTTPsClient();
        return httpsClient;
    }

}
