package web;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Um wrapper para a classe {@link ServletOutputStream} que envolve uma instância de {@link ByteArrayOutputStream}.
 * O conteúdo gravado através deste {@link ServletOutputStream} pode ser recuperado chamando o método {@link #getContent()}.
 */
public class ServletOutputStreamWrapper extends ServletOutputStream {

    private ByteArrayOutputStream outputStream;

    /**
     * Cria uma nova instância de {@link ServletOutputStreamWrapper}.
     * @param outputStream a instância de {@link ByteArrayOutputStream} que será envolvida.
     */
    public ServletOutputStreamWrapper(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Retorna true, indicando que a saída pode ser gravada a qualquer momento.
     * @return true, sempre.
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * Não faz nada, já que esta classe é apenas utilizada para testes e não suporta escrita assíncrona.
     * @param listener o {@link WriteListener} a ser definido.
     */
    @Override
    public void setWriteListener(WriteListener listener) {
        // Nenhuma implementação necessária para testes
    }

    /**
     * Grava um byte no {@link ByteArrayOutputStream} encapsulado.
     * @param b o byte a ser gravado.
     * @throws IOException caso ocorra um erro de I/O ao gravar o byte.
     */
    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    /**
     * Retorna o conteúdo gravado no {@link ByteArrayOutputStream} encapsulado.
     * @return um array de bytes representando o conteúdo gravado.
     */
    public byte[] getContent() {
        return outputStream.toByteArray();
    }
}
