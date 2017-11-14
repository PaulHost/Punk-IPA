package ph.hostev.paul.punk_ipa.api;

import ph.hostev.paul.punk_ipa.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static PunkApi punkApi;
    private static APIClient singleton;

    private APIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        punkApi = retrofit.create(PunkApi.class);
    }

    public static APIClient getInstance() {
        if (singleton == null) singleton = new APIClient();
        return singleton;
    }

    public PunkApi getPunkApi() {
        return punkApi;
    }

}
