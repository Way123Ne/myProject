package fragment.wayne.com.chunvzuo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.wayne.com.chunvzuo.R;
import adapter.wayne.com.zhunvzuo.MyPagerAdapter;
import qrcodedemo.zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Wayne on 2018/6/9.
 */

public class HomepageFragment extends Fragment {
    private EditText et_typing;
    private ViewPager viewpager;
    private TextView tv_desc;
    private LinearLayout ll_point_group;
    private Button btn_scan;
    private TextView tv_scan;
    private WebView webView;
    // 图片资源ID
    private final int[] imageIds = {R.mipmap.a, R.mipmap.b, R.mipmap.c,
            R.mipmap.d, R.mipmap.e};
    // 图片标题集合
    private final String[] imageDescriptions = {"巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀"};
    // 页面切换后的上一个位置
    private int lastPosition;
    private List<ImageView> imageList;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
//    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    private boolean isRunning = false;
    private Context context;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            System.out.println("handleMessage(android.os.Message msg====="
                    + msg);

            viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);

            if (isRunning) {
                // Message ms = Message.obtain();
                // ms.what=22;
                // ms.obj = "xxxxxx";
                // handler.sendMessage(ms);
                handler.sendEmptyMessageDelayed(99, 3000);
            }
        }

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_homepage, container,
                false);
//        ButterKnife.bind(getActivity());

        findId(view);
        loadWeb();
        init();
        setListener();
        return view;

    }

    private void findId(View view) {
        // TODO Auto-generated method stub
        et_typing = (EditText) view.findViewById(R.id.et_typing);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        tv_desc.setText(imageDescriptions[0]);
        ll_point_group = (LinearLayout) view.findViewById(R.id.ll_point_group);
        btn_scan = (Button) view.findViewById(R.id.btn_scan);
        webView = (WebView) view.findViewById(R.id.wv_html);
    }

    private void loadWeb() {
        String url = "https://www.yhd.com/";
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    private void init() {
        // 准备工作
        imageList = new ArrayList<ImageView>();

        for (int i = 0; i < imageIds.length; i++) {
            // this：当前类对象的引用
            // getActivity:用在fragment中,用于获取fragment对应的父类
            ImageView image = new ImageView(this.getActivity());
            image.setBackgroundResource(imageIds[i]);
            imageList.add(image);

            // 添加指示点
            ImageView point = new ImageView(this.getActivity());

            // LayoutParams 的类型和要该view的父view的类型一致
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, -2);
            params.leftMargin = 15;
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            ll_point_group.addView(point);

        }

        viewpager.setAdapter(new MyPagerAdapter(imageList));
    }

    private void setListener() {
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            /**
             * 当选择的页面发生改变的时候，回调此方法
             * position 选择的新的页面
             */
            public void onPageSelected(int position) {

                position = position % imageList.size();

                tv_desc.setText(imageDescriptions[position]);

                // 改变指示点的状态，
                // 让上一个位置的点，设置 enable 为 false
                ll_point_group.getChildAt(lastPosition).setEnabled(false);
                // 让当前位置的点，设置enable 为true
                ll_point_group.getChildAt(position).setEnabled(true);

                // 更新上一个位置的值
                lastPosition = position;

            }

            @Override
            // 当页面在滑动上，不断的调用
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            // 当页面的滑动状发生改变的时候，回调 ， 状态有：按下，滑动，抬起，
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 设置viewPager当前页面的页面,item 是页面的位置

        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                % imageList.size();
        viewpager.setCurrentItem(item);

        isRunning = true;
        handler.sendEmptyMessageDelayed(99, 3000);


        btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });

    }

    public ViewPager getViewpager() {
        return viewpager;
    }

    public void setViewpager(ViewPager viewpager) {
        this.viewpager = viewpager;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //显示扫描到的内容
            tv_scan.setText(bundle.getString("result"));
        }

    }

}
