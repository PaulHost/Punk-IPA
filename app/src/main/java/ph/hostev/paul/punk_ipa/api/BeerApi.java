package ph.hostev.paul.punk_ipa.api;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.beans.SortParameters;

import static ph.hostev.paul.punk_ipa.Constants.ABV_GT;
import static ph.hostev.paul.punk_ipa.Constants.ABV_LT;
import static ph.hostev.paul.punk_ipa.Constants.BEERS;
import static ph.hostev.paul.punk_ipa.Constants.EBC_GT;
import static ph.hostev.paul.punk_ipa.Constants.EBC_LT;
import static ph.hostev.paul.punk_ipa.Constants.IBU_GT;
import static ph.hostev.paul.punk_ipa.Constants.IBU_LT;
import static ph.hostev.paul.punk_ipa.Constants.PAGE;

public class BeerApi extends HTTPs {

    @NonNull
    public Single<List<Beer>> getBeerList(@Nullable Integer page, @Nullable SortParameters param) {
        StringBuilder sb = new StringBuilder();
        sb.append(BEERS).append(PAGE).append(page);
        if (param != null) {
            if (param.getAbv_gt() > 0) sb.append(ABV_GT).append(param.getAbv_gt());
            if (param.getAbv_lt() > 0) sb.append(ABV_LT).append(param.getAbv_lt());
            if (param.getEbc_gt() > 0) sb.append(EBC_GT).append(param.getEbc_gt());
            if (param.getEbc_lt() > 0) sb.append(EBC_LT).append(param.getEbc_lt());
            if (param.getIbu_gt() > 0) sb.append(IBU_GT).append(param.getIbu_gt());
            if (param.getIbu_lt() > 0) sb.append(IBU_LT).append(param.getIbu_lt());
        }
        String url = sb.toString();

        return this.get(url)
                   .flatMap(this::beers);
    }
}
