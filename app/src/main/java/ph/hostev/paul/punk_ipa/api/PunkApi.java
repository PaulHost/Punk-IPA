package ph.hostev.paul.punk_ipa.api;

import java.util.List;
import java.util.Map;

import ph.hostev.paul.punk_ipa.api.modules.PunkModule;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static ph.hostev.paul.punk_ipa.Constants.BEER;

public interface PunkApi {
    @Headers({
            "Accept: application/json",
            "accept-encoding: gzip, deflate",
            "accept-language: en-US,en;q=0.8",
            "user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"
    })
    @GET(BEER)
    Call<List<PunkModule>> getBeerList(@Query("page") int page);

    @GET(BEER + "{id}")
    Call<PunkModule> getBeer(@Path("id") int id);

    @GET(BEER)
    Call<List<PunkModule>> getSort(@HeaderMap Map<String, String> heders, @Path("page") int page);
}
