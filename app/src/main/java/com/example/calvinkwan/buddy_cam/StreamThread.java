package com.example.tjmelanson.buddycam;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;


/**
 * Created by tjmelanson on 7/23/15.
 * In hindsight, create a separate class for the thread,
 * which would integrate all of the streaming and apps
 */

public class StreamThread extends Thread{

    //In order to add a mail notification / image push, add an object reference to the class in the constructor
    //and call the appropriate methods in the class

    private InetAddress mAddr;
    private int mPort;
    private Socket mSocket;

    private InputStreamReader mInput = null;

    //Size of the image read in--change depending on input
    private int IMAGE_SIZE = 1024;

    private byte[] mBuffer;
    private MatOfByte mob;

    private static final char[] JPG_START  ={0xff, 0xd8};
    private static final char[] JPG_END    ={0xff, 0xd9};



    public StreamThread(InetAddress addr, int port){
        mAddr = addr;
        mPort = port;
        mBuffer = new byte[IMAGE_SIZE];
        mob = new MatOfByte();

    }


    public boolean connect(){
        try {
            if (mSocket!=null) {
                //Error checking for redoing already existing socket
            }
            mSocket = new Socket(mAddr, mPort);
            mInput = new InputStreamReader(mSocket.getInputStream());
        } catch (IOException e){
            return false;
        }

        return true;
    }

    //Finds the index of a key in the array (Java doesn't support this as far as I know)
    public int findIndex(char[] buffer, char[] key){
        int len = key.length;
        for (int i=0; i<=buffer.length-len; i++){
            if (key.equals(Arrays.copyOfRange(buffer, i, i+len))) return i;
        }
        return -1;
    }

    //Takes an snapshot from a connected socket -- only works if socket outputs one image per client query
    public Mat readImage() throws IOException {

        //Read the data into a raw byte array
        char [] c_buff = new char[IMAGE_SIZE];
        mInput.read(c_buff, 0, IMAGE_SIZE);

        //Find jpg within the byte array, which is 0xff 0xd8 to 0xff 0xd9 inclusive
        int start = findIndex(c_buff, JPG_START);
        int end = findIndex(c_buff, JPG_END) + 2;
        if (start==-1 || end==-1) return null;
        //Save to main buffer
        mBuffer = Arrays.copyOfRange(c_buff, start, end).toString().getBytes();


        //output as MatOfByte, which is a Mat object made from a byte array
        mob.fromArray(mBuffer);
        return (Mat) mob;
    }



    @Override
    public void run() {

        while (true) {
            if (!connect()) return;

            try {
                Mat image = readImage();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            //Process image here
        }

    }
}
