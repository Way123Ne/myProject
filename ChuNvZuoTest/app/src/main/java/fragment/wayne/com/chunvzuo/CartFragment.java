package fragment.wayne.com.chunvzuo;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import activity.wayne.com.chunvzuo.NewActivity;
import activity.wayne.com.chunvzuo.R;
import entity.wayne.com.zhunvzuo.Goods;

/**
 * Created by Wayne on 2018/6/9.
 */

public class CartFragment extends Fragment /*implements AbsListView.OnScrollListener*/ {
    //    private ListView list;
//    private int scrollState;
//    private int count = 12;
//    private int lastItem;
//    private int visibleItemCount;
//    private Button btn_more;
//    private LinearLayout ll_loading;
//    private View view;
//    private ListAdapter listAdapter = new ListAdapter();

    CheckBox btn_all;
    ArrayList<Goods> lists;
    MyAdapter adapter;
    ListView listView;
    TextView all_money;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_cart2, container, false);
//        ButterKnife.bind(getActivity());

//        findId(view);
//        ll_loading.setVisibility(View.GONE);
//
//        setListener();

        btn_all = (CheckBox) view.findViewById(R.id.btn_all);
        lists = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.listView);
        all_money = (TextView) view.findViewById(R.id.all_money);
        for (int i = 1; i < 11; i++) {
            lists.add(new Goods("商品" + i, i * 10));
        }
        adapter = new MyAdapter(getActivity(), lists);
        listView.setAdapter(adapter);

        btn_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < lists.size(); i++) {
                    adapter.isSelected.put(i, isChecked);
                }
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    public void changeMoney(int money) {
        all_money.setText("总价:" + money);

    }

    public class MyAdapter extends BaseAdapter {
        // 上下文
        Context context;
        // 数据源
        ArrayList<Goods> lists;

        //        MainActivity mainActivity;
//        CartFragment cartFragment;
        int MONEY = 0;

        public HashMap<Integer, Boolean> isSelected;

        public MyAdapter(Context context, ArrayList<Goods> lists) {
            this.context = context;
            this.lists = lists;
            isSelected = new HashMap<>();
//        mainActivity = (MainActivity) context;
//            this.getActivity() = (Activity) context;
            initData();
        }

        private void initData() {
            for (int i = 0; i < lists.size(); i++) {
                isSelected.put(i, false);
            }
        }

        @Override
        public int getCount() {
            return lists == null ? 0 : lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final MyHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
                holder = new MyHolder();
                holder.cb = (CheckBox) convertView.findViewById(R.id.item_check);
                holder.tv = (TextView) convertView.findViewById(R.id.item_text);
                holder.price = (TextView) convertView.findViewById(R.id.item_price);
                holder.ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
                convertView.setTag(holder);
            } else {
                holder = (MyHolder) convertView.getTag();
            }

            holder.cb.setChecked(isSelected.get(position));
            holder.tv.setText(lists.get(position).name);
            holder.price.setText(lists.get(position).price + "");

            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /*if(isChecked){    // 这个写法影响到商品的计价，取消选择商品时不会减价。
                    isSelected.put(position,isChecked);
                }*/
                    isSelected.put(position, isChecked);

                    MONEY = 0;
                    for (int i = 0; i < lists.size(); i++) {
                        if (isSelected.get(i)) {
                            MONEY += lists.get(i).price;
                        }
                    }
//                mainActivity.changeMoney(MONEY);
//                cartFragment = new CartFragment();
//                cartFragment.changeMoney(MONEY);
                    changeMoney(MONEY);
                }
            });

            holder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), NewActivity.class));
                }
            });

            return convertView;
        }

        class MyHolder {
            TextView tv, price;
            CheckBox cb;
            LinearLayout ll_item;
        }
    }


}

//    return view;

//
//    private void findId(View view) {
//        list = (ListView) view.findViewById(R.id.list);
//        btn_more = (Button) view.findViewById(R.id.btn_more);
//        ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
//    }
//
//    private void setListener() {
//       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Intent intent = new Intent(getActivity(), NewActivity.class);
//               startActivity(intent);
//           }
//       });
//        btn_more.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if (lastItem == listAdapter.count
//                        && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    btn_more.setVisibility(View.GONE);
//                    ll_loading.setVisibility(View.VISIBLE);
//
//                }
//                if (listAdapter.count < count) {
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            listAdapter.count += 6;
//                            listAdapter.notifyDataSetChanged();
//                            btn_more.setVisibility(View.VISIBLE);
//                            ll_loading.setVisibility(View.VISIBLE);
//
//                        }
//                    }, 2000);
//                } else if (listAdapter.count == count) {
//                    ll_loading.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        // list = getListView();
//        // list.addFooterView(view);
//        list.setAdapter(listAdapter);
//        list.setOnScrollListener(this);
//    }
//
//    class ListAdapter extends BaseAdapter {
//        private int count = 6;
//
//        @Override
//        public int getCount() {
//            // TODO Auto-generated method stub
//            return count;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            // TODO Auto-generated method stub
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            // TODO Auto-generated method stub
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // TODO Auto-generated method stub
//            LayoutInflater inflater = LayoutInflater.from(getActivity());
//            View view = inflater.inflate(R.layout.other_listview_item, null);
//            TextView tv_good_name = (TextView) view
//                    .findViewById(R.id.tv_good_name);
//            TextView tv_order_num = (TextView) view
//                    .findViewById(R.id.tv_order_num);
//            TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
//            // tv.setText("商品名称:");
//            return view;
//        }
//    }
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        this.scrollState = scrollState;
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        this.visibleItemCount = visibleItemCount;
//        lastItem = firstVisibleItem + visibleItemCount - 1;
//        System.out.println(listAdapter.count);
////		if (listAdapter.count >= count) {
////			list.removeFooterView(view);
////		}
//    }
//}
