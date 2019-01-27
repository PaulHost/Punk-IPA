package ph.hostev.paul.punk_ipa.data.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Single;

abstract class HTTPs extends BeerParser {

    @Nullable
    private HttpsURLConnection conn;
    @Nullable
    private InputStream mIs = null;

    @NonNull
    Single<String> get(@NonNull String url) {
        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                mIs = conn.getInputStream();
            } else {
                mIs = conn.getErrorStream();
            }

            return readInputStream(mIs);

        } catch (IOException e) {
            return Single.error(e);
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

    @NonNull
    private Single<String> readInputStream(@NonNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return Single.just(result.toString("UTF-8"));
    }
}

