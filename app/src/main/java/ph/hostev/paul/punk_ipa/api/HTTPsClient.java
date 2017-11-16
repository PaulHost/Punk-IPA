package ph.hostev.paul.punk_ipa.api;

import java.util.List;

import ph.hostev.paul.punk_ipa.beans.Beer;
import ph.hostev.paul.punk_ipa.beans.SortHeader;
import ph.hostev.paul.punk_ipa.parsing.BeerParser;

import static ph.hostev.paul.punk_ipa.Constants.BEERS;
import static ph.hostev.paul.punk_ipa.Constants.PAGE;

public class HTTPsClient {

    private HTTPs https;
    private BeerParser parser;
    public HTTPsClient(){
        https = new HTTPs();
        parser = new BeerParser();
    }

    public void get(final int page, final SortHeader params, final Callback<List<Beer>> listCallback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append(BEERS).append(PAGE).append(page);
                https.get(sb.toString(), params , new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        listCallback.onSuccess(parser.beers(s));
                    }
                });
            }
        }).start();
    }
}
