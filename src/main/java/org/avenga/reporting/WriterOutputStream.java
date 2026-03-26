package org.avenga.reporting;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

public class WriterOutputStream extends OutputStream {

    @Getter
    @Setter
    private static String requestLog;

    @Getter
    @Setter
    private static String responseLog;

    private final Writer writer;
    private static String log;

    public static StringWriter requestWriter = new StringWriter();
    public static PrintStream requestCapture = new PrintStream(new WriterOutputStream(requestWriter), false);

    public static StringWriter responseWriter = new StringWriter();
    public static PrintStream responseCapture = new PrintStream(new WriterOutputStream(responseWriter), false);

    public static String writeRequestLog() throws IOException {
        log = requestWriter.toString();
        requestWriter.getBuffer().setLength(0);
        return log;
    }

    public static String writeResponseLog() {
        log = responseWriter.toString();
        responseWriter.getBuffer().setLength(0);
        return log;
    }

    public WriterOutputStream(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(int b) throws IOException {
        writer.write(b);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}