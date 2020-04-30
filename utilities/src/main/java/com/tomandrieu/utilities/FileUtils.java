package com.tomandrieu.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileUtils {
    public static boolean fileExist(Context context, String localPath) {
        File file = new File(context.getFilesDir(), localPath);
        return file.exists();
    }
    public static boolean writeFileInternal(Context context, String fileName, String jsonString) {
        try {
            Log.e("=>try to write at ", fileName);
            File file = new File(context.getFilesDir(), fileName);
            Log.e("=>file locate", file.getAbsolutePath());

            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(jsonString.getBytes());
            } catch (Exception e) {e.printStackTrace();
            } finally {
                stream.close();
            }
            return true;
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();fileNotFound.printStackTrace();
            return false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            ioException.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Must have check that file exist before calling this method
     *
     * @param context
     * @param path
     * @return
     */
    public static JSONArray getJSONArrayFromLocalFile(Context context, String path) {
        try {
            File yourFile = new File(context.getFilesDir(), path);
            FileInputStream stream = new FileInputStream(yourFile);
            String jsonStr = null;

            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            jsonStr = Charset.defaultCharset().decode(bb).toString();

            stream.close();

            JSONArray jsonArray = new JSONArray((jsonStr));
            return jsonArray;
        } catch (JSONException e) {e.printStackTrace();
            return null;
        } catch (IOException e) {e.printStackTrace();
            return null;
        }
    }

    public static class JsonTask extends AsyncTask<String, String, JSONObject> {

        private final SeeykoListeners.JSONObjectListener listener;
        private final Context context;

        public JsonTask(Context context, SeeykoListeners.JSONObjectListener listener) {
            this.context = context;
            this.listener = listener;
        }


        protected void onPreExecute() {
            super.onPreExecute();
            listener.load();
        }

        protected JSONObject doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                URLConnection urlConn = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                return new JSONObject(stringBuffer.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Log.e("fileUtils", "finally ");
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            listener.callback(result);
        }
    }

    public static class JsonArrayTask extends AsyncTask<String, String, JSONArray> {

        private final SeeykoListeners.JSONArrayListener listener;
        private final Context context;

        public JsonArrayTask(Context context, SeeykoListeners.JSONArrayListener listener) {
            this.context = context;
            this.listener = listener;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("download", "start");
            listener.load();
        }

        protected JSONArray doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                URLConnection urlConn = url.openConnection();
                Log.e("download", "size of: " + url + " = " + urlConn.getContentLength());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                return new JSONArray(stringBuffer.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Log.e("download", "finish");
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            listener.callback(result);
        }
    }
}

