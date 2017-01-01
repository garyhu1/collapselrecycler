package com.garyhu.citypickerdemo.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.base.BaseFragment;
import com.garyhu.citypickerdemo.widget.myrecyclerview.CircleTextView;
import com.garyhu.citypickerdemo.widget.myrecyclerview.City;
import com.garyhu.citypickerdemo.widget.myrecyclerview.CityAdapter;
import com.garyhu.citypickerdemo.widget.myrecyclerview.MySlideView;
import com.garyhu.citypickerdemo.widget.myrecyclerview.StickyDecoration;
import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public class ThirdFragment extends BaseFragment  implements MySlideView.onTouchListener, CityAdapter.onItemClickListener{

    @BindView(R.id.my_slide_view)
    MySlideView mySlideView;
    @BindView(R.id.my_circle_view)
    CircleTextView circleTxt;
    @BindView(R.id.rv_sticky_example)
    RecyclerView recyclerView;

    public  static List<City> cityList = new ArrayList<>();
    private Set<String> firstPinYin = new LinkedHashSet<>();
    public static List<String> pinyinList = new ArrayList<>();
    private PinyinComparator pinyinComparator;

    private CityAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_third;
    }

    @Override
    protected void initData() {
        initView();
    }

    @Override
    protected void setListener() {

    }

    private void initView() {

        cityList.clear();
        firstPinYin.clear();
        pinyinList.clear();

        pinyinComparator = new PinyinComparator();
        for (int i = 0; i < City.stringCitys.length; i++) {
            City city = new City();
            city.setCityName(City.stringCitys[i]);
            city.setCityPinyin(transformPinYin(City.stringCitys[i]));
            cityList.add(city);
        }
        Collections.sort(cityList, pinyinComparator);
        for (City city : cityList) {
            firstPinYin.add(city.getCityPinyin().substring(0, 1));

        }
        for (String string : firstPinYin) {
            pinyinList.add(string);
        }
        mySlideView.setListener(this);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CityAdapter(getActivity(), cityList);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new StickyDecoration(getActivity()));
    }

    @Override
    public void itemClick(int position) {
        Toast.makeText(getActivity(), "你选择了:" + cityList.get(position).getCityName(), Toast.LENGTH_SHORT).show();
    }

    public String transformPinYin(String character) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < character.length(); i++) {
            buffer.append(Pinyin.toPinyin(character.charAt(i)));
        }
        return buffer.toString();
    }

    @Override
    public void showTextView(String textView, boolean dismiss) {

        if (dismiss) {
            circleTxt.setVisibility(View.GONE);
        } else {
            circleTxt.setVisibility(View.VISIBLE);
            circleTxt.setText(textView);
        }

        int selectPosition = 0;
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).getFirstPinYin().equals(textView)) {
                selectPosition = i;
                break;
            }
        }
        scrollPosition(selectPosition);
    }


    public class PinyinComparator implements Comparator<City> {
        @Override
        public int compare(City cityFirst, City citySecond) {
            return cityFirst.getCityPinyin().compareTo(citySecond.getCityPinyin());
        }
    }


    private void scrollPosition(int index) {
        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            recyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = recyclerView.getChildAt(index - firstPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            recyclerView.scrollToPosition(index);
        }
    }

}
