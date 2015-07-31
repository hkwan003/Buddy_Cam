package com.example.calvinkwan.buddy_cam;

import android.provider.SyncStateContract;
import android.util.Log;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by calvinkwan on 7/31/15.
 */
public class OpenCVTransfer extends AsyncTask<Void, OpenCVTransfer.InetHelper.Wrapper, Void > implements NetworkHelper
{
    public class InetHelper {

        private static final String TAG = "InetHelper";

        Handler controlHandle;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

        InetAddress serverAddress;
        Socket serverSocket;

        Context context;
        private ProgressDialog dialog;

        boolean connected = false;

        /*
         * Streaming stuff
         */
        int bytes, size;
        byte[] data;
        Mat buff, rev, ret;
        Bitmap bmp;

        @Override
        public void connect(Context context) {
            Log.i(TAG, "Attempting to connect via Inet");

            //TODO: try to put connection stuff in connect()

            this.context = context;
            dialog = new ProgressDialog(this.context);

            this.execute();
        }

        @Override
        public void disconnect() {
            Log.i(TAG, "Attempting to disconnect via Inet");

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void read() {
        }

        public void write() {

        }


        @Override
        protected Void doInBackground(final Void ... unused)
        {

            Wrapper wrapper = new Wrapper();

            try {
                serverAddress = InetAddress.getByName(Constants.SERVER_IP);
                serverSocket = new Socket();
                serverSocket.connect(new InetSocketAddress(Constants.SERVER_IP, Constants.SERVER_PORT), 5000);
            } catch (Exception e ) {
                e.printStackTrace();
            }

            wrapper.type = 0;
            wrapper.status = serverSocket.isConnected();
            publishProgress(wrapper);

            try {
                Thread.sleep(500);

                dataInputStream = new DataInputStream(serverSocket.getInputStream());
                dataOutputStream = new DataOutputStream(serverSocket.getOutputStream());

                wrapper.type = 1;

                while (serverSocket.isConnected()) {
                    bytes = 0;

                    size = dataInputStream.readInt();
                    data = new byte[size];

                    for (int i = 0; i < size; i+= bytes) {
                        bytes = dataInputStream.read(data, i, size - i);
                    }

                    buff = new Mat(1, size, CvType.CV_8UC1);
                    buff.put(0, 0, data);

                    rev = Highgui.imdecode(buff, Highgui.CV_LOAD_IMAGE_UNCHANGED);

                    Imgproc.cvtColor(rev, ret, Imgproc.COLOR_RGB2BGR);

                    wrapper.img = ret;
                    publishProgress(wrapper);
                    Thread.sleep(75);
                }

            } catch (Exception e ) {
                e.printStackTrace();
            }

            return null;
        }

        class Wrapper
        {
            int type;
            Boolean status;
            Mat img;
        }


        @Override
        protected void onPostExecute(Void unused) {
            if (this.dialog.isShowing()) {
                Log.i(TAG, "Force closing dialog.");
                this.dialog.dismiss();
            }
        }

        public boolean isConnected() {
            return connected;
        }
    }
}
