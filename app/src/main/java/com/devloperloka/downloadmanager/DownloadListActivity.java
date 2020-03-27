package com.devloperloka.downloadmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huxq17.download.Pump;
import com.huxq17.download.core.DownloadInfo;
import com.huxq17.download.core.DownloadListener;
import com.huxq17.download.utils.LogUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DownloadListActivity<call, jk> extends AppCompatActivity {
    //  ArrayList<String> id ;
    //  ArrayList<Long>size ;

    int counter;
    boolean value;
    private HashMap<DownloadViewHolder, DownloadInfo> map = new HashMap<>();
    DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            DownloadInfo downloadInfo = getDownloadInfo();
            DownloadViewHolder viewHolder = (DownloadViewHolder) downloadInfo.getExtraData();
            if (viewHolder != null) {
                DownloadInfo tag = map.get(viewHolder);
                if (tag != null && tag.getId().equals(downloadInfo.getId())) {
                    viewHolder.bindData(downloadInfo);
                }
            }


        }

        @Override
        public void onFailed() {
            super.onFailed();
            LogUtil.e("onFailed code=" + getDownloadInfo().getErrorCode());
        }
    };
    private RecyclerView recyclerView;
    private DownloadAdapter downloadAdapter;
    private List<DownloadInfo> downloadInfoList;
    private String tag;

    public static void start(Context context, String tag) {
        Intent intent = new Intent(context, DownloadListActivity.class);
        intent.putExtra("tag", tag);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = getIntent().getStringExtra("tag");
        setContentView(R.layout.activity_download_list);
        downloadListener.enable();
        recyclerView = findViewById(R.id.rvDownloadList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //Get all download list.
        downloadInfoList = TextUtils.isEmpty(tag) ? Pump.getAllDownloadList() : Pump.getDownloadListByTag(tag);


        //Sort download list if need.
        Collections.sort(downloadInfoList, new Comparator<DownloadInfo>() {
            @Override
            public int compare(DownloadInfo o1, DownloadInfo o2) {
                return (int) (o1.getCreateTime() - o2.getCreateTime());
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        downloadAdapter = new DownloadAdapter(map, downloadInfoList);
        recyclerView.setAdapter(downloadAdapter);
        ArrayList<String> id;
        id = loadId();
        ArrayList<Long> size;
        size = loadSize();
        counter = getData();
        value = getBoolean();
        String sjf3 = getString();


        if (sjf3 == "SJF") {


            //Stop pause algorithm acc to array sorted by QuickSort//

            if (counter == 1 && value) {
                int small = scheduler.arrange(size);
                //  Pump.stop(id.get(small));
                Pump.resume(id.get(small));
                saveBoolean(false);
            } else if (counter == 2 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                saveBoolean(false);
            } else if (counter == 3 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.stop(id.get(small + 2));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                Pump.resume((id.get(small + 2)));
                saveBoolean(false);
            } else if (counter == 4 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.stop(id.get(small + 2));
                Pump.stop(id.get(small + 3));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                Pump.resume((id.get(small + 2)));
                Pump.resume((id.get(small + 3)));
                saveBoolean(false);
            } else if (counter == 5 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.stop(id.get(small + 2));
                Pump.stop(id.get(small + 3));
                Pump.stop(id.get(small + 4));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                Pump.resume((id.get(small + 2)));
                Pump.resume((id.get(small + 3)));
                Pump.resume((id.get(small + 4)));
                saveBoolean(false);
            } else if (counter == 6 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.stop(id.get(small + 2));
                Pump.stop(id.get(small + 3));
                Pump.stop(id.get(small + 4));
                Pump.stop(id.get(small + 5));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                Pump.resume((id.get(small + 2)));
                Pump.resume((id.get(small + 3)));
                Pump.resume((id.get(small + 4)));
                Pump.resume((id.get(small + 5)));
                saveBoolean(false);
            } else if (counter == 7 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.stop(id.get(small + 2));
                Pump.stop(id.get(small + 3));
                Pump.stop(id.get(small + 4));
                Pump.stop(id.get(small + 5));
                Pump.stop(id.get(small + 6));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                Pump.resume((id.get(small + 2)));
                Pump.resume((id.get(small + 3)));
                Pump.resume((id.get(small + 4)));
                Pump.resume((id.get(small + 5)));
                Pump.resume((id.get(small + 6)));
                saveBoolean(false);
            } else if (counter == 8 && value) {
                int small = scheduler.arrange(size);
                Pump.stop(id.get(small));
                Pump.stop(id.get(small + 1));
                Pump.stop(id.get(small + 2));
                Pump.stop(id.get(small + 3));
                Pump.stop(id.get(small + 4));
                Pump.stop(id.get(small + 5));
                Pump.stop(id.get(small + 6));
                Pump.stop(id.get(small + 7));
                Pump.resume(id.get(small));
                Pump.resume((id.get(small + 1)));
                Pump.resume((id.get(small + 2)));
                Pump.resume((id.get(small + 3)));
                Pump.resume((id.get(small + 4)));
                Pump.resume((id.get(small + 5)));
                Pump.resume((id.get(small + 6)));
                Pump.resume((id.get(small + 7)));
                saveBoolean(false);
            }
        }
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
            return new ArrayList<>();
        } else return data;

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

    public int getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveCounter", MODE_PRIVATE);
        return sharedPreferences.getInt("counterValue", MODE_PRIVATE);
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

    public static class DownloadAdapter extends RecyclerView.Adapter<DownloadViewHolder> {
        List<DownloadInfo> downloadInfoList;
        HashMap<DownloadViewHolder, DownloadInfo> map;

        public DownloadAdapter(HashMap<DownloadViewHolder, DownloadInfo> map, List<DownloadInfo> downloadInfoList) {
            this.downloadInfoList = downloadInfoList;
            this.map = map;
        }

        @NonNull
        @Override
        public DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_download_list, viewGroup, false);
            return new DownloadViewHolder(v, this);
        }

        @Override
        public void onBindViewHolder(@NonNull DownloadViewHolder viewHolder, int i) {
            DownloadInfo downloadInfo = downloadInfoList.get(i);
            downloadInfo.setExtraData(viewHolder);
            map.put(viewHolder, downloadInfo);

            viewHolder.bindData(downloadInfo);
        }

        public void delete(DownloadViewHolder viewHolder) {
            int position = viewHolder.getAdapterPosition();
            downloadInfoList.remove(position);
            notifyItemRemoved(position);
            map.remove(viewHolder);
        }

        @Override
        public int getItemCount() {
            return downloadInfoList.size();
        }

    }

    public static class DownloadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ProgressBar progressBar;
        TextView tvName;
        TextView tvStatus;
        TextView tvSpeed;
        TextView tvDownload;
        DownloadInfo downloadInfo;
        DownloadInfo.Status status;
        AlertDialog dialog;

        public DownloadViewHolder(@NonNull View itemView, final DownloadAdapter adapter) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.pb_progress);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStatus = itemView.findViewById(R.id.bt_status);
            tvSpeed = itemView.findViewById(R.id.tv_speed);
            tvDownload = itemView.findViewById(R.id.tv_download);
            tvStatus.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            dialog = new AlertDialog.Builder(itemView.getContext())
                    .setTitle("Confirm delete?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.delete(DownloadViewHolder.this);
                            Pump.deleteById(downloadInfo.getId());
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }

        public void bindData(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
            this.status = downloadInfo.getStatus();
            tvName.setText(downloadInfo.getName());
            String speed = "";
            int progress = downloadInfo.getProgress();
            progressBar.setProgress(progress);


            switch (status) {
                case STOPPED:
                    tvStatus.setText("Start");
                    break;
                case PAUSING:
                    tvStatus.setText("Pausing");
                    break;
                case PAUSED:
                    tvStatus.setText("Continue");
                    break;
                case WAIT:
                    tvStatus.setText("Waiting");
                    break;
                case RUNNING:
                    tvStatus.setText("Pause");
                    speed = downloadInfo.getSpeed();
                    break;
                case FINISHED:
                    tvStatus.setText("Saved");
                    break;
                case FAILED:
                    tvStatus.setText("Retry");
                    break;
            }
            tvSpeed.setText(speed);
            long completedSize = downloadInfo.getCompletedSize();
            long totalSize = downloadInfo.getContentLength();
            tvDownload.setText(Utils.getDataSize(completedSize) + "/" + Utils.getDataSize(totalSize));
        }

        @Override
        public void onClick(View v) {
            if (v == tvStatus) {
                switch (status) {
                    case PAUSING:

                    case STOPPED:
                    case PAUSED:
                    case FAILED:
                        Pump.resume(downloadInfo.getId());
                        break;

                    case WAIT:

                        //do nothing.
                        break;
                    case RUNNING:
                        Pump.pause(downloadInfo.getId());


                        break;
                    case FINISHED:


                        break;
                }
            }

        }

        @Override
        public boolean onLongClick(View v) {
            dialog.show();
            return true;
        }


    }
}



