package com.ggp.noob;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author:GGP
 * @Date:2020/6/19 23:31
 * @Description:
 */
public class ProxyHandler extends Thread {
    private InputStream input;
    private OutputStream output;

    public ProxyHandler(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            while (true) {
                output.write(input.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
