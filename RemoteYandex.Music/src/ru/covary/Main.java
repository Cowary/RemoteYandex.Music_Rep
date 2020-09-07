package ru.covary;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    ServerSocket ss;
    Socket socket;
    InputStream in;
    DataInputStream inData;
    int port;
    String code;
    InetAddress thisIP;

    public static void main(String[] args) {
        System.out.println("kek");
        Main m = new Main();
        while (true){
            m.startServer();
            try {
                m.socket.close();
                m.ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
    public void startServer() {
        port = 9056;
        try {
            thisIP = InetAddress.getLocalHost();
            System.out.println("IP: " + thisIP.getHostAddress());
            ss = new ServerSocket(port);
            socket = ss.accept();
            System.out.println("Клиент подклчен.");
            in = socket.getInputStream();
            inData = new DataInputStream(in);

            while (true) {
                code = inData.readUTF().trim();
                System.out.println(code);
                switch (code) {
                    case "CodePlayStopTreck":
                        pressKey(KeyEvent.VK_CONTROL, KeyEvent.VK_P);
                        break;
                    case "CodePreviousTreck":
                        pressKey(KeyEvent.VK_CONTROL, KeyEvent.VK_B);
                        break;
                    case "CodeNextTreck":
                        pressKey(KeyEvent.VK_CONTROL, KeyEvent.VK_F);
                        break;
                    case "CodeRepeatTreck":
                        pressKey(KeyEvent.VK_CONTROL, KeyEvent.VK_T);
                        break;
                    case "CodeRandomTreck":
                        pressKey(KeyEvent.VK_CONTROL, KeyEvent.VK_H);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void pressKey(int keven_1, int keven_2){
        Robot r = null;
        try {
            r = new Robot();
            r.keyPress(keven_1);
            r.keyPress(keven_2);
            r.keyRelease(keven_2);
            r.keyRelease(keven_1);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
