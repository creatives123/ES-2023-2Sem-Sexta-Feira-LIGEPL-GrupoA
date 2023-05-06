package web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class MockFileItem implements FileItem {
    private final InputStream content;
    private final String fileName;

    /**
     * Constrói um novo {@code MockFileItem} com os parâmetros especificados.
     *
     * @param content  o conteúdo do arquivo a ser enviado
     * @param fileName o nome do arquivo a ser enviado
     */
    public MockFileItem(InputStream content, String fileName) {
        this.content = content;
        this.fileName = fileName;
    }

    /**
     * Retorna um {@code InputStream} que pode ser usado para ler o conteúdo do
     * arquivo.
     *
     * @return um {@code InputStream} que pode ser usado para ler o conteúdo do
     *         arquivo
     * @throws IOException se houver um erro de entrada/saída
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return content;
    }

    /**
     * Retorna o nome do arquivo.
     *
     * @return o nome do arquivo
     */
    @Override
    public String getName() {
        return fileName;
    }

    /**
     * Retorna o tamanho do arquivo.
     *
     * @return o tamanho do arquivo
     */
    @Override
    public long getSize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna o tipo de conteúdo do arquivo.
     *
     * @return o tipo de conteúdo do arquivo
     */
    @Override
    public String getContentType() {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna o nome do campo do formulário usado para enviar o arquivo.
     *
     * @return o nome do campo do formulário usado para enviar o arquivo
     */
    @Override
    public String getFieldName() {
        throw new UnsupportedOperationException();
    }

    /**
     * Define o nome do campo do formulário usado para enviar o arquivo.
     *
     * @param name o novo nome do campo do formulário usado para enviar o arquivo
     */
    @Override
    public void setFieldName(String name) {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna se o item é um campo do formulário regular ou um campo de arquivo.
     *
     * @return {@code true} se o item for um campo do formulário regular,
     *         {@code false} caso contrário
     */
    @Override
    public boolean isFormField() {
        throw new UnsupportedOperationException();
    }

    /**
     * Define se o item é um campo do formulário regular ou um campo de arquivo.
     *
     * @param state o novo estado do item: {@code true} se o item for um campo do
     *              formulário regular,
     *              {@code false} caso contrário
     */
    @Override
    public void setFormField(boolean state) {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna um objeto OutputStream para gravação dos dados do item.
     *
     * @return um objeto OutputStream
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna o conteúdo do item como um array de bytes.
     *
     * @return o conteúdo do item como um array de bytes
     */
    @Override
    public byte[] get() {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna o conteúdo do item como uma string, usando a codificação
     * especificada.
     *
     * @param encoding a codificação a ser usada
     * @return o conteúdo do item como uma string
     * @throws UnsupportedEncodingException se a codificação especificada não for
     *                                      suportada
     */
    @Override
    public String getString(String encoding) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna o conteúdo do item como uma string, usando a codificação padrão.
     *
     * @return o conteúdo do item como uma string
     */
    @Override
    public String getString() {
        throw new UnsupportedOperationException();
    }

    /**
     * Grava o conteúdo do item em um arquivo especificado.
     *
     * @param file o arquivo no qual gravar o conteúdo do item
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public void write(java.io.File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Exclui o item.
     */
    @Override
    public void delete() {
        throw new UnsupportedOperationException();
    }

    /**
     * Retorna os cabeçalhos do item.
     *
     * @return os cabeçalhos do item
     */
    @Override
    public FileItemHeaders getHeaders() {
        throw new UnsupportedOperationException("Método 'getHeaders' não implementado.");
    }

    /**
     * Define os cabeçalhos do item.
     *
     * @param arg0 os cabeçalhos do item
     */
    @Override
    public void setHeaders(FileItemHeaders arg0) {
        throw new UnsupportedOperationException("Método 'setHeaders' não implementado.");
    }

    /**
     * Verifica se o conteúdo do item está armazenado em memória.
     *
     * @return true se o conteúdo do item estiver armazenado em memória; false caso
     *         contrário
     */
    @Override
    public boolean isInMemory() {
        throw new UnsupportedOperationException("Método 'isInMemory' não implementado.");
    }
}
