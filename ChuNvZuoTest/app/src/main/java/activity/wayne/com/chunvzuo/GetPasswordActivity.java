package activity.wayne.com.chunvzuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

/**
 * Created by Wayne on 2018/6/9.
 */

public class GetPasswordActivity extends AppCompatActivity implements OnTouchListener {
    private Button btn_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
//        ButterKnife.bind(this);

        findId();
        setListener();
    }

    private void findId() {
        btn_sure = (Button) findViewById(R.id.btn_sure);
    }

    private void setListener() {
        btn_sure.setOnTouchListener(this);

        //确定按钮监听
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(GetPasswordActivity.this,
                        NewPasswordActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
