package com.iigo.peasloadingview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.iigo.library.PeasLoadingView;

public class MainActivity extends AppCompatActivity {
    private PeasLoadingView peasLoadingView1;
    private PeasLoadingView peasLoadingView2;
    private PeasLoadingView peasLoadingView3;
    private PeasLoadingView peasLoadingView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peasLoadingView1 = findViewById(R.id.plv_loading1);
        peasLoadingView2 = findViewById(R.id.plv_loading2);
        peasLoadingView3 = findViewById(R.id.plv_loading3);
        peasLoadingView4 = findViewById(R.id.plv_loading4);

        peasLoadingView4.setPeasCount(7);
        peasLoadingView4.setInterpolator(new LinearInterpolator());
        peasLoadingView4.setPeasColors(new int[]{Color.RED, Color.WHITE, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.GRAY});
    }


    public void onStart(View view) {
        peasLoadingView1.start();
        peasLoadingView2.start();
        peasLoadingView3.start();
        peasLoadingView4.start();
    }

    public void onStop(View view) {
        peasLoadingView1.stop();
        peasLoadingView2.stop();
        peasLoadingView3.stop();
        peasLoadingView4.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //on size changed
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) peasLoadingView4.getLayoutParams();
        layoutParams.height = 50;
        layoutParams.width = 50;
        peasLoadingView4.setLayoutParams(layoutParams);

        return super.onKeyDown(keyCode, event);
    }
}

