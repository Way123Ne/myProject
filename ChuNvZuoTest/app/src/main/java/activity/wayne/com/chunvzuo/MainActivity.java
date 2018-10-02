package activity.wayne.com.chunvzuo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import fragment.wayne.com.chunvzuo.CartFragment;
import fragment.wayne.com.chunvzuo.ClassificationFragment;
import fragment.wayne.com.chunvzuo.HomepageFragment;
import fragment.wayne.com.chunvzuo.ProfileFragment;

/**
 * Created by Wayne on 2018/6/9.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_homepage, tv_classification, tv_cart, tv_profile;
    private HomepageFragment homepagefragment;
    private ClassificationFragment classificationfragment;
    private CartFragment cartfragment;
    private ProfileFragment profilefragment;
    private FrameLayout fl_container;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        findId();
        setListener();
        initUI();
    }

    private void findId() {
        tv_homepage = (TextView) findViewById(R.id.tv_homepage);
        tv_classification = (TextView) findViewById(R.id.tv_classification);
        tv_cart = (TextView) findViewById(R.id.tv_cart);
        tv_profile = (TextView) findViewById(R.id.tv_profile);
        fl_container = (FrameLayout) findViewById(R.id.fl_container);
    }

    private void initUI() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        homepagefragment = new HomepageFragment();
        transaction.add(R.id.fl_container, homepagefragment);
        transaction.commit();

        tv_homepage.setSelected(true);
    }

    private void setListener() {
        tv_homepage.setOnClickListener(this);
        tv_classification.setOnClickListener(this);
        tv_cart.setOnClickListener(this);
        tv_profile.setOnClickListener(this);
    }
    public void hideAllFragment(FragmentTransaction transaction) {
        if (homepagefragment != null) {
            transaction.hide(homepagefragment);
        }

        if (classificationfragment != null) {
            transaction.hide(classificationfragment);
        }

        if (cartfragment != null) {
            transaction.hide(cartfragment);
        }

        if (profilefragment != null) {
            transaction.hide(profilefragment);
        }
    }
    @Override
    public void onClick(View v) {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()) {
            case R.id.tv_homepage:
                selected();
                tv_homepage.setSelected(true);
                if (homepagefragment == null) {
                    homepagefragment = new HomepageFragment();
                    transaction.add(R.id.fl_container, homepagefragment);
                } else {
                    transaction.show(homepagefragment);
                }
                break;
            case R.id.tv_classification:
                selected();
                tv_classification.setSelected(true);
                if (classificationfragment == null) {
                    classificationfragment = new ClassificationFragment();
                    transaction.add(R.id.fl_container, classificationfragment);
                } else {
                    transaction.show(classificationfragment);
                }
                break;
            case R.id.tv_cart:
                selected();
                tv_cart.setSelected(true);
                if (cartfragment == null) {
                    cartfragment = new CartFragment();
                    transaction.add(R.id.fl_container, cartfragment);
                } else {
                    transaction.show(cartfragment);
                }
                break;
            case R.id.tv_profile:
                selected();
                tv_profile.setSelected(true);
                if (profilefragment == null) {
                    profilefragment = new ProfileFragment();
                    transaction.add(R.id.fl_container, profilefragment);
                } else {
                    transaction.show(profilefragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    public void selected() {
        // TODO Auto-generated method stub
        tv_homepage.setSelected(false);
        tv_classification.setSelected(false);
        tv_cart.setSelected(false);
        tv_profile.setSelected(false);
    }


}
