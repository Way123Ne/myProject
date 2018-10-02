package activity.wayne.com.chunvzuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Wayne on 2018/6/9.
 */

public class SendSmsActivity extends AppCompatActivity implements View.OnTouchListener {
    private Button btn_fastsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msm);
//        ButterKnife.bind(this);

        findId();
        setListener();
    }

    private void findId() {
        btn_fastsend = (Button) findViewById(R.id.btn_fastsend);
    }

    private void setListener() {
        //快速发送按钮监听
        btn_fastsend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(Intent.ACTION_VIEW);
                it.putExtra("sms_body", "The SMS text");
//				it.setType("vnd.android-dir/mms-sms");
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
