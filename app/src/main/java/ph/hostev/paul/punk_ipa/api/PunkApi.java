package ph.hostev.paul.punk_ipa.api;

import java.util.List;
import java.util.Map;

import ph.hostev.paul.punk_ipa.api.modules.PunkModule;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

import static ph.hostev.paul.punk_ipa.Constants.BEER;
import static ph.hostev.paul.punk_ipa.Constants.PAGE;

public interface PunkApi {
    @GET(BEER + PAGE)
    Call<List<PunkModule>> getBeerList(@Path("page") int page);

    @GET(BEER + "{id}")
    Call<PunkModule> getBeer(@Path("id") int id);

    @GET(BEER + PAGE)
    Call<List<PunkModule>> getSort(@HeaderMap Map<String, String> heders, @Path("page") int page);
}
