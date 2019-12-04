package dev.app.ks.thinkit.onenote.framework.communicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import dev.app.ks.thinkit.onenote.framework.communicate.property.HttpStatusCode;
import dev.app.ks.thinkit.onenote.framework.communicate.property.RequestMethod;

public final class Request {

    private static final int CONNECTION_TIMEOUT = 100000;
    private static final int READ_TIMEOUT = 100000;

    private static final String DEFAULT_CHARSET = "utf-8";

    private String response;

    private HttpStatusCode httpStatusCode;

    private static String makeRequestUrl(String url, Map<String, String> queryMap) {

        if (queryMap.isEmpty()) {
            return url;
        }

        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(url);

        if (queryMap.isEmpty()) {
            return requestUrl.toString();
        }

        requestUrl.append("?");

        for (Map.Entry<String, String> entrySet : queryMap.entrySet()) {
            requestUrl.append(entrySet.getKey())
                    .append("=")
                    .append(entrySet.getValue())
                    .append("&");
        }

        // 通信処理上問題はないが末尾の余分な"&"を除去
        requestUrl.setLength(requestUrl.length() - 1);

        return requestUrl.toString();
    }

    private static HttpURLConnection connect(
            String requestUrl,
            RequestMethod method) throws IOException {

        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method.getMethodName());
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setDoOutput(false);
        connection.setDoInput(true);
        connection.connect();

        return connection;
    }

    private static boolean isValidStatusCode(HttpURLConnection con) throws IOException {
        return con.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    private static String getStringResponse(HttpURLConnection conn) throws IOException {

        StringBuilder response = new StringBuilder();

        InputStream in = conn.getInputStream();

        String encoding = conn.getContentEncoding();
        InputStreamReader inReader = new InputStreamReader(in, encoding != null ? encoding : DEFAULT_CHARSET);
        BufferedReader bufferedReader = new BufferedReader(inReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }

        bufferedReader.close();
        inReader.close();
        in.close();
        conn.disconnect();

        return response.toString();
    }

    public boolean send(String url, RequestMethod method) {
        return send(url, method, new HashMap<String, String>());
    }

    public boolean send(
            String url,
            RequestMethod method,
            Map<String, String> queryMap) {

        String requestUrl = makeRequestUrl(url, queryMap);

        try {
            HttpURLConnection connection = connect(requestUrl, method);

            if (isValidStatusCode(connection)) {
                String response = getStringResponse(connection);
                setResponse(response);
            }

            HttpStatusCode httpStatusCode
                    = HttpStatusCode.getStatusFromCode(connection.getResponseCode());

            setHttpStatusCode(httpStatusCode);

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public String getResponse() {
        return response;
    }

    private void setResponse(String response) {
        this.response = response;
    }

    public HttpStatusCode getHttpStatus() {
        return httpStatusCode;
    }

    private void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
