package web.CalendarServlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Esta classe é uma implementação de {@link HttpURLConnection} usada para simular
 * uma conexão HTTP durante testes unitários. É utilizada para testar o método
 * {@link WebCallImporterServlet#importFromUrl(String,javax.servlet.http.HttpServletRequest)}.
 */
public class HttpURLConnectionStub extends HttpURLConnection {
    private InputStream inputStream;
    private boolean throwIOException;

    /**
     * Construtor para a conexão HTTP simulada, que recebe uma URL e um objeto {@link InputStream}.
     * @param url a URL da conexão.
     * @param inputStream o {@link InputStream} que será usado para obter o conteúdo da conexão.
     */
    protected HttpURLConnectionStub(URL url, InputStream inputStream) {
        super(url);
        this.inputStream = inputStream;
    }

    /**
     * Construtor para a conexão HTTP simulada, que recebe um objeto {@link HttpURLConnection}.
     * @param connection a conexão {@link HttpURLConnection} que será usada para obter o conteúdo.
     * @throws IOException se ocorrer um erro de I/O.
     */
    public HttpURLConnectionStub(HttpURLConnection connection) throws IOException {
        super(new URL("http://localhost"));
        this.inputStream = connection.getInputStream();
    }

    /**
     * Construtor para a conexão HTTP simulada, que recebe uma String com um conteúdo válido 
     * de resposta HTTP. Será usado um {@link ByteArrayInputStream} para transformar a String em um {@link InputStream}.
     * @param validContent o conteúdo válido de resposta HTTP que será usado para a conexão simulada.
     */
    public HttpURLConnectionStub(String validContent) {
        super(null);
        this.inputStream = new ByteArrayInputStream(validContent.getBytes());
    }

    /**
     * Implementação do método {@link HttpURLConnection#disconnect()} que não faz nada.
     */
    @Override
    public void disconnect() {
    }

    /**
     * Implementação do método {@link HttpURLConnection#usingProxy()} que sempre retorna false.
     */
    @Override
    public boolean usingProxy() {
        return false;
    }

    /**
     * Implementação do método {@link HttpURLConnection#connect()} que não faz nada 
     * @throws IOException se ocorrer um erro de I/O.
     */
    @Override
    public void connect() throws IOException {
    }

    /**
     * Implementação do método {@link HttpURLConnection#getInputStream()} que retorna o 
     * {@link InputStream} previamente definido para a conexão.
     * @return o {@link InputStream} previamente definido para a conexão.
     * @throws IOException se ocorrer um erro de I/O.
     */
    @Override
    public InputStream getInputStream() throws IOException {
        if (throwIOException) {
            throw new IOException("Test IOException");
        }
        return inputStream;
    }

    /**
     * Implementação do método {@link HttpURLConnection#setRequestMethod(String)} que não faz nada.
     * @param method o método HTTP que será definido (GET, POST, etc).
     * @throws ProtocolException se ocorrer um erro de protocolo.
     */
    @Override
    public void setRequestMethod(String method) throws ProtocolException {
    }

    /**
     * Implementação do método {@link HttpURLConnection#setRequestProperty(String, String)} que não faz nada.
     * @param key a chave que será definida.
     * @param value o valor que será definido.
     */
    @Override
    public void setRequestProperty(String key, String value) {
    }

    /**
     * Define se o método {@link HttpURLConnection#getInputStream()} deve lançar
     * uma exceção {@link IOException} na próxima chamada.
     * @param throwIOException
     */
    public void setThrowIOException(boolean throwIOException) {
        this.throwIOException = throwIOException;
    }
}
