package com.example.lab5_multythreading;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import model.DownloadTask;
import model.SlowTask;

public class MainActivity extends AppCompatActivity implements DownloadTask.DownloadListener{

    // Exercise 1
    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC;
    private int intTest;
    private Thread bgThread;
    private Handler handler;
    private Button btnStart;

    // Exercise 2
    private ProgressBar pbWaiting;
    private TextView tvTopCaption;
    private EditText etInput;
    private Button btnExecute;
    private int globalValue, accum;
    private long startTime;
    private final String PATIENCE = "Some important data is being collected now.\nPlease be patient...wait...";
    private Runnable fgRunnable, bgRunnable;
    private Thread testThread;

    // Exercise 3
    private Button btnQuickJob, btnSlowJob;
    private TextView tvStatus;
    private SlowTask slowTask;

    // Music Player
    private TextView tvTime, tvDuration;
    private SeekBar seekBarTime, seekBarVolume;
    private Button btnPlay, btnPause;
    private MediaPlayer mediaPlayer;
    private DownloadTask downloadTask;
    ImageView imageView;
    private boolean isRotated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.ex_1);
//        Exercise1();

//        setContentView(R.layout.ex_2);
//        Exercise2();

//        setContentView(R.layout.ex_3);
//        Exercise3();

        setContentView(R.layout.media_player);
        MusicPlayer();
    }

    private void Exercise1() {
        findViewByIds_Ex1();
        initVariables_Ex1();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = true;
                pbFirst.setVisibility(View.VISIBLE);
                pbSecond.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                bgThread.start();
            }
        });
    }

    private void findViewByIds_Ex1() {
        pbFirst = (ProgressBar) findViewById(R.id.pb_first);
        pbSecond = (ProgressBar) findViewById(R.id.pb_second);
        tvMsgWorking = (TextView) findViewById(R.id.tv_working);
        tvMsgReturned = (TextView) findViewById(R.id.tv_return);
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    @SuppressLint("HandlerLeak")
    private void initVariables_Ex1() {
        isRunning = false;
        MAX_SEC = 20;
        intTest = 1;
        pbFirst.setMax(MAX_SEC);
        pbFirst.setProgress(0);

        pbFirst.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String returnedValue = (String) msg.obj;
                String result = getString(R.string.returned_by_bg_thread) + returnedValue;
                tvMsgReturned.setText(result);
                pbFirst.incrementProgressBy(2);

                if (pbFirst.getProgress() == MAX_SEC) {
                    String stopped = getString(R.string.done_background_thread_has_been_stopped);
                    tvMsgReturned.setText(stopped);
                    tvMsgWorking.setText(getString(R.string.done));
                    pbFirst.setVisibility(View.GONE);
                    pbSecond.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                    isRunning = false;
                } else {
                    String working = getString(R.string.working) + pbFirst.getProgress();
                    tvMsgWorking.setText(working);
                }
            }
        };
    }

    private void Exercise2() {
        findViewByIds_Ex2();
        initVariables_Ex2();

        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etInput.getText().toString();
                Toast.makeText(MainActivity.this, "You said: " + input, Toast.LENGTH_SHORT).show();
            }
        });

        testThread.start();
        pbWaiting.incrementProgressBy(0);
    }
    private  void findViewByIds_Ex2() {
        tvTopCaption = (TextView) findViewById(R.id.tv_top_caption);
        pbWaiting = (ProgressBar) findViewById(R.id.pb_waiting);
        etInput = (EditText) findViewById(R.id.et_input);
        btnExecute = (Button) findViewById(R.id.btn_execute);
    }
    private void initVariables_Ex2() {
        globalValue = 0;
        accum = 0;
        startTime = System.currentTimeMillis();
        handler = new Handler();

        fgRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int progressStep = 5;
                    double totalTime = (System.currentTimeMillis() - startTime) / 1000;
                    synchronized (this) {
                        globalValue += 100;
                    }

                    tvTopCaption.setText(PATIENCE + totalTime  + " - " + globalValue);
                    pbWaiting.incrementProgressBy(progressStep);
                    accum += progressStep;

                    if (accum >= pbWaiting.getMax()) {
                        tvTopCaption.setText(getString(R.string.bg_work_is_over));
                        pbWaiting.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Log.e("fgRunnable", e.getMessage());
                }
            }
        };

        bgRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        Thread.sleep(1000);

                        synchronized (this) {
                            globalValue += 1;
                        }

                        handler.post(fgRunnable);
                    }
                } catch (Exception e) {
                    Log.e("bgRunnable", e.getMessage());
                }
            }
        };

        testThread = new Thread(bgRunnable);
    }

    private void Exercise3() {
        findViewByIds_Ex3();
        slowTask=new SlowTask(MainActivity.this, tvStatus);

        btnQuickJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                tvStatus.setText(sdf.format(new Date()));
            }
        });
        btnSlowJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slowTask.execute();
            }
        });
    }

    private void findViewByIds_Ex3() {
        btnQuickJob = (Button) findViewById(R.id.btn_quick_job);
        btnSlowJob = (Button) findViewById(R.id.btn_slow_job);
        tvStatus = (TextView) findViewById(R.id.tv_status);
    }

    private void initBgThread() {
        bgThread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < MAX_SEC && isRunning; i++) {
                        Thread.sleep(1000);

                        Random rnd = new Random();
                        String data = "Thread value: " + (int) rnd.nextInt(101) +
                                (getString(R.string.global_value_seen) + " " + intTest);
                        intTest++;

                        if (isRunning) {
                            Message msg = handler.obtainMessage(1, (String) data);
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Throwable t) {
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    private void MusicPlayer() {
        findViewByIds_MP();
        downloadTask = new DownloadTask(MainActivity.this);
        downloadTask.execute("https://drive.google.com/uc?id=1YNKBycKwMOlfg1Wf9-KsYqGxkWKdeEpn");

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://drive.google.com/uc?id=1YNKBycKwMOlfg1Wf9-KsYqGxkWKdeEpn");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPause.setVisibility(View.GONE);
        btnPlay.setVisibility(View.VISIBLE);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);

                mediaPlayer.start();
                rotateImage();

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                mediaPlayer.pause();
                stopRotation();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (downloadTask != null) {
            downloadTask.cancel(true);
        }
    }

    private void findViewByIds_MP() {
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
    }

    @Override
    public void onDownloadComplete(InputStream inputStream) {

    }

    @Override
    public void onDownloadFailed() {

    }

    private RotateAnimation rotateAnimation;
    private void rotateImage() {
        imageView = findViewById(R.id.disk);
        rotateAnimation = new RotateAnimation(
                0,
                360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        // Đặt tốc độ xoay và chiều dài của animation
        rotateAnimation.setInterpolator(new LinearInterpolator()); // Tốc độ đều
        rotateAnimation.setDuration(10000); // Thời gian 1 vòng là 3 giây
        rotateAnimation.setRepeatCount(Animation.INFINITE); // Lặp vô hạn
        rotateAnimation.setRepeatMode(Animation.RESTART);

        imageView.startAnimation(rotateAnimation);

    }
    private void stopRotation() {
        if (rotateAnimation != null) {
            imageView.clearAnimation();
        }
    }
}