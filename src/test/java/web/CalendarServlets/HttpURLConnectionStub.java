package web.CalendarServlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpURLConnectionStub extends HttpURLConnection {
    private InputStream inputStream;
    private boolean throwIOException;

    protected HttpURLConnectionStub(URL url, InputStream inputStream) {
        super(url);
        this.inputStream = inputStream;
    }

    public HttpURLConnectionStub(HttpURLConnection connection) throws IOException {
        super(new URL("http://localhost"));
        this.inputStream = connection.getInputStream();
    }

    public HttpURLConnectionStub(String validContent) {
        super(null);
        this.inputStream = new ByteArrayInputStream(validContent.getBytes());
    }

    @Override
    public void disconnect() {
    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (throwIOException) {
            throw new IOException("Test IOException");
        }
        return inputStream;
    }

    @Override
    public void setRequestMethod(String method) throws ProtocolException {
    }

    @Override
    public void setRequestProperty(String key, String value) {
    }

    public void setThrowIOException(boolean throwIOException) {
        this.throwIOException = throwIOException;
    }
}
