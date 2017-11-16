package ph.hostev.paul.punk_ipa.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ph.hostev.paul.punk_ipa.beans.SortHeader;

import static ph.hostev.paul.punk_ipa.Constants.ABV_GT;
import static ph.hostev.paul.punk_ipa.Constants.ABV_LT;
import static ph.hostev.paul.punk_ipa.Constants.EBC_GT;
import static ph.hostev.paul.punk_ipa.Constants.EBC_LT;
import static ph.hostev.paul.punk_ipa.Constants.IBU_GT;
import static ph.hostev.paul.punk_ipa.Constants.IBU_LT;

class HTTPs {

    private HttpsURLConnection conn;
    private InputStream mIs = null;

    synchronized void get(final String url, SortHeader params, final Callback<String> pResponse) {

        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            if (params != null) {
                conn.setRequestProperty(ABV_GT, params.getAbv_gt());
                conn.setRequestProperty(ABV_LT, params.getAbv_lt());
                conn.setRequestProperty(IBU_GT, params.getIbu_gt());
                conn.setRequestProperty(IBU_LT, params.getIbu_lt());
                conn.setRequestProperty(EBC_GT, params.getEbc_gt());
                conn.setRequestProperty(EBC_LT, params.getEbc_lt());
            }

            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                mIs = conn.getInputStream();
            } else {
                mIs = conn.getErrorStream();
            }

            final String response = readInputStream(mIs);


            if (pResponse != null) {
                pResponse.onSuccess(response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (mIs != null) {
                try {
                    mIs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}

