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

    public MockFileItem(InputStream content, String fileName) {
        this.content = content;
        this.fileName = fileName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return content;
    }

    @Override
    public String getName() {
        return fileName;
    }

    // Other required methods from FileItem interface should be implemented as well.
    // You can leave the implementation empty or throw an UnsupportedOperationException
    // if they are not used in your test case.

    @Override
    public long getSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getContentType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFieldName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFieldName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFormField() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setFormField(boolean state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] get() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(String encoding) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(java.io.File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FileItemHeaders getHeaders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeaders'");
    }

    @Override
    public void setHeaders(FileItemHeaders arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHeaders'");
    }

    @Override
    public boolean isInMemory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInMemory'");
    }
}
