package com.cowtowncoder.jsonperf.util;

import java.io.IOException;
import java.io.Writer;

public class NopWriter extends Writer
{
    @Override
    public void write(int ch) throws IOException { }

    @Override
    public void write(char[] cbuf) throws IOException { }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException { }

    @Override
    public void flush() throws IOException { }

    @Override
    public void close() throws IOException { }
}
