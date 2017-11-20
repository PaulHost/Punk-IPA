package ph.hostev.paul.punk_ipa.api;

import java.util.List;

import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.beans.SortParameters;
import ph.hostev.paul.punk_ipa.parsing.BeerParser;

import static ph.hostev.paul.punk_ipa.Constants.ABV_GT;
import static ph.hostev.paul.punk_ipa.Constants.ABV_LT;
import static ph.hostev.paul.punk_ipa.Constants.BEERS;
import static ph.hostev.paul.punk_ipa.Constants.EBC_GT;
import static ph.hostev.paul.punk_ipa.Constants.EBC_LT;
import static ph.hostev.paul.punk_ipa.Constants.IBU_GT;
import static ph.hostev.paul.punk_ipa.Constants.IBU_LT;
import static ph.hostev.paul.punk_ipa.Constants.PAGE;

public class HTTPsClient {

    private HTTPs https;
    private BeerParser parser;

    public HTTPsClient() {
        https = new HTTPs();
        parser = new BeerParser();
    }

    public void get(final int page, final SortParameters param, final Callback<List<Beer>> listCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
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

                https.get(url, new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        listCallback.onSuccess(parser.beers(s));
                    }
                });
            }
        }).start();
    }
}
