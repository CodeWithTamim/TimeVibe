package com.nasahacker.timvibe.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nasahacker.timvibe.R;
import com.nasahacker.timvibe.databinding.ActivityMainBinding;
import com.nasahacker.timvibe.util.AppUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int time;
    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarted) {
                    //stop
                    stopTimer();
                    binding.btnStartPause.setImageResource(R.drawable.play_arrow_24px);
                } else {
                    //start
                    startTimer();
                    binding.btnStartPause.setImageResource(R.drawable.pause_24px);
                }
            }
        });


        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                binding.tvTimer.setText(AppUtil.getFormatedTime(0));
                binding.progressIndicator.setProgress(AppUtil.getMinutePercentage(0));
                binding.btnStartPause.setImageResource(R.drawable.play_arrow_24px);

            }
        });


        mTimer = new Timer();


    }

    private void startTimer() {
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvTimer.setText(AppUtil.getFormatedTime(time));
                        binding.progressIndicator.setProgress(AppUtil.getMinutePercentage(time));
                    }
                });
            }
        };

        mTimer.schedule(mTimerTask, 0, 1000);
        isStarted = true;
    }

    private void stopTimer() {
        mTimerTask.cancel();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvTimer.setText(AppUtil.getFormatedTime(time));
                binding.progressIndicator.setProgress(AppUtil.getMinutePercentage(time));
            }
        });
        isStarted = false;
    }

}