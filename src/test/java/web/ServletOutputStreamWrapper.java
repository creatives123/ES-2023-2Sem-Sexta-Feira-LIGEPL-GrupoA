package web;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletOutputStreamWrapper extends ServletOutputStream {

    private ByteArrayOutputStream outputStream;

    public ServletOutputStreamWrapper(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
        // No implementation needed for testing
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }
}
