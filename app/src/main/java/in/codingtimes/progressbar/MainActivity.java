package in.codingtimes.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pBar,pBar1,pBar2;
    private int a = 0;
    private TextView textView;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        pBar = findViewById(R.id.p_Bar);
        Button button = findViewById(R.id.show_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = pBar.getProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(a < 100){
                            a +=1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pBar.setProgress(a);
                                    textView.setText(a + "/"+ pBar.getMax());
                                    if(a == 100)
                                        textView.setText("Your Progress has been Completed");
                                }
                            });
                            try {
                                Thread.sleep(50);
                            }catch (InterruptedException e){e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}