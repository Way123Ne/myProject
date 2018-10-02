package fragment.wayne.com.chunvzuo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.wayne.com.chunvzuo.R;

/**
 * Created by Wayne on 2018/6/14.
 */

public class ClothesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_clothes, null);
        return view;
    }
}
