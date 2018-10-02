package fragment.wayne.com.chunvzuo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import activity.wayne.com.chunvzuo.R;

/**
 * Created by Wayne on 2018/6/9.
 */

public class ClassificationFragment extends Fragment {

    private RadioGroup chat_tab_menu;
    private FurnitureFragment main_furniture;
    private ClothesFragment main_clothes;
    private FoodFragment main_food;
    private CarFragment main_car;
    private FruitFragment main_fruit;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_classification,
                container, false);
//        ButterKnife.bind(getActivity());

//        findId(view);
//        init();
        GroupChang(view);
        return view;
    }

    // 主页面点击按钮Fragment改变
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void GroupChang(View view) {
        // 在chat页面中嵌套fragment
        main_furniture = new FurnitureFragment();
//		getFragmentManager().beginTransaction().replace(R.id.main_chat_content, main_chat_news).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.main_chat_content, main_furniture).commit();
        chat_tab_menu = (RadioGroup) view.findViewById(R.id.chat_tab_menu);
        chat_tab_menu
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @SuppressLint("NewApi")
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        switch (checkedId) {
                            case R.id.main_furniture:
                                main_furniture = new FurnitureFragment();
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_chat_content,
                                                main_furniture).commit();
                                break;
                            case R.id.main_clothes:
                                if (main_clothes == null) {
                                    main_clothes = new ClothesFragment();
                                }
                                Log.i("MyFragment", "ClothesFragment");
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_chat_content,
                                                main_clothes).commit();
                                break;
                            case R.id.main_food:
                                main_food = new FoodFragment();
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_chat_content,
                                                main_food).commit();
                                break;
                            case R.id.main_car:
                                main_car = new CarFragment();
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_chat_content,
                                                main_car).commit();
                                break;
                            case R.id.main_fruit:
                                main_fruit = new FruitFragment();
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_chat_content,
                                                main_fruit).commit();
                                break;
                            default:
                                break;
                        }

                    }
                });
    }
}


