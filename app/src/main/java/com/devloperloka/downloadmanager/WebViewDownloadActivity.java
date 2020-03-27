package com.devloperloka.downloadmanager;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huxq17.download.Pump;
import com.huxq17.download.utils.LogUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import static com.devloperloka.downloadmanager.QuickSort.sort;


public class WebViewDownloadActivity extends AppCompatActivity {

    public int counter = 0;
    public boolean value;
    String sjf;
    String fcfs;
    private ProgressDialog progressDialog;
    private com.huxq17.download.core.DownloadListener downloadListener = new com.huxq17.download.core.DownloadListener() {

        @Override
        public void onProgress(int progress) {
            progressDialog.setProgress(progress);
        }

        @Override
        public void onSuccess() {
            progressDialog.dismiss();

            Toast.makeText(WebViewDownloadActivity.this, "Download Finished", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            progressDialog.dismiss();
            LogUtil.e("onFailed code=" + getDownloadInfo().getErrorCode());
            Toast.makeText(WebViewDownloadActivity.this, "Download failed", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        final WebView webView = findViewById(R.id.webview);
        getSupportActionBar().setTitle("Browser");

        Intent intent = getIntent();
        fcfs = intent.getStringExtra("fcfs");
        sjf = intent.getStringExtra("sjf");

        //Browser enabling in Activity
        WebViewClient webViewClient = new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        initProgressDialog();
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setMinimumFontSize(12);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        final String newUA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Safari/602.1.50";
        webView.getSettings().setUserAgentString(newUA);
        //Homepage Website
        webView.loadUrl("https://www.google.com/");

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {

                //ArrayList of Downloading Files &data written fro server

                String sjf2 = "SJF";
                if (sjf2.equals(getString())) {

                    ArrayList<String> id;
                    ArrayList<Long> size;
                    ArrayList<String> idNo = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));

                    //Loading Array list and other data from Cache memory
                    counter = getData();
                    value = getBoolean();

                    size = loadSize();
                    if (counter == 0) {
                        id = idNo;
                        saveId(id);
                    } else {
                        id = loadId();
                    }

                    //saving data to cache Memory
                    size.add(counter, contentLength);
                    saveSize(size);
                    //Creating new download request onClick sjf
                    Pump.newRequest(url)
                            .listener(downloadListener)
                            .setId(id.get(counter))
                            .submit();

                    if (counter == 0) {
                        Pump.stop("1");
                    } else if (counter == 1) {
                        Pump.stop("2");
                    } else if (counter == 2) {
                        Pump.stop("3");
                    } else if (counter == 3) {
                        Pump.stop("4");
                    } else if (counter == 4) {
                        Pump.stop("5");
                    } else if (counter == 5) {
                        Pump.stop("6");
                    } else if (counter == 6) {
                        Pump.stop("7");
                    } else if (counter == 7) {
                        Pump.stop("8");
                    }
                    //sort array acc to size of files
                    sort(size, id, 0, counter);


                    //saving data to cache Memory
                    saveId(id);
                    saveSize(size);
                    saveBoolean(true);
                    counter++;
                    saveData();
                    Toast.makeText(WebViewDownloadActivity.this, "Download Started SJF", Toast.LENGTH_LONG).show();

                } else {
                    //Creating new download request onClick fcfs

                    Pump.newRequest(url)
                            .listener(downloadListener)
                            .submit();
                    Toast.makeText(WebViewDownloadActivity.this, "Download Started FCFS", Toast.LENGTH_LONG).show();


                }

            }

        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        downloadListener.disable();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Downloading");
        //progressDialog.setMessage("Downloading now...");
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }


    //Method for saving and loading data from cache memory
    public void saveId(ArrayList<String> id) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(id);
        editor.putString("task list", json);
        editor.apply();
    }

    public ArrayList<String> loadId() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);

        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> data;

        data = gson.fromJson(json, type);
        if (data == null) {
            return data = new ArrayList<>();
        } else return data;
    }

    public void saveSize(ArrayList<Long> size) {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(size);
        editor.putString("task list1", json);
        editor.apply();
    }

    public ArrayList<Long> loadSize() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list1", null);

        Type type = new TypeToken<ArrayList<Long>>() {
        }.getType();
        ArrayList<Long> data;

        data = gson.fromJson(json, type);
        if (data == null) {
            return new ArrayList<>();
        } else return data;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveCounter", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counterValue", counter);
        editor.apply();
    }

    public int getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveCounter", MODE_PRIVATE);
        counter = sharedPreferences.getInt("counterValue", MODE_PRIVATE);
        return counter;
    }

    public void saveBoolean(boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences("saveBoolean", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("saveBoolean", value);
        editor.apply();
    }

    public boolean getBoolean() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveBoolean", MODE_PRIVATE);
        value = sharedPreferences.getBoolean("saveBoolean", value);
        return value;
    }

    public String getString() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveString", MODE_PRIVATE);
        String a = sharedPreferences.getString("saveString", null);
        return a;
    }

}
