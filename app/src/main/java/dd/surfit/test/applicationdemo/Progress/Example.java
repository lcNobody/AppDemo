package dd.surfit.test.applicationdemo.Progress;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/4/1.
 */
public class Example extends Activity {
    private RoundProgressBar mRoundProgressBar1, mRoundProgressBar2 ,mRoundProgressBar3, mRoundProgressBar4, mRoundProgressBar5, mRoundProgressBar6;
    private EditText etProgress;
    private int progress = 0;
    private int max = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_example);

        mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.roundProgressBar1);
        mRoundProgressBar2 = (RoundProgressBar) findViewById(R.id.roundProgressBar2);
        mRoundProgressBar3 = (RoundProgressBar) findViewById(R.id.roundProgressBar3);
        mRoundProgressBar4 = (RoundProgressBar) findViewById(R.id.roundProgressBar4);
        mRoundProgressBar5 = (RoundProgressBar) findViewById(R.id.roundProgressBar5);
        mRoundProgressBar6 = (RoundProgressBar) findViewById(R.id.roundProgressBar6);
        etProgress = (EditText) findViewById(R.id.et_progress);

        ((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        if(etProgress.getText() !=null && !TextUtils.isEmpty(etProgress.getText().toString())) {
                            max = Integer.parseInt(etProgress.getText().toString());
                        }else{
                            max = 100;
                        }
                        progress = 0;
                        while(progress < max ){
                            progress += 2;

                            System.out.println(progress);

                            mRoundProgressBar1.setProgress(progress);
                            mRoundProgressBar2.setProgress(progress);
                            mRoundProgressBar3.setProgress(progress);
                            mRoundProgressBar4.setProgress(progress);
                            mRoundProgressBar5.setProgress(progress);
                            mRoundProgressBar6.setProgress(progress);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });

    }


}
