package activity.wayne.com.chunvzuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Wayne on 2018/6/9.
 */

public class RegisterAccoutActivity extends AppCompatActivity implements View.OnTouchListener{
    private ImageButton ib_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findId();
        setListener();
    }

    private void findId() {
        ib_return = (ImageButton) findViewById(R.id.ib_return);
    }

    private void setListener() {
        ib_return.setOnTouchListener(this);

        //返回监听
        ib_return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(RegisterAccoutActivity.this,
                        LoginActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
