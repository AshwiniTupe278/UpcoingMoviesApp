package com.ashwini.upcomingmoviesapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ashwini.upcomingmoviesapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by softdotcom-7 on 2/1/18.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList movieImageUrl;
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context mContext, ArrayList movieImageUrl) {
        this.mContext = mContext;
        this.movieImageUrl = movieImageUrl;
    }

    @Override
    public int getCount() {
        return movieImageUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_images, null);

        ImageView images = view.findViewById(R.id.ivMovieImages_1);

        Picasso.with(mContext).load(movieImageUrl.get(position).toString()).resize(200,200).into(images);

        Log.d("Images",movieImageUrl.get(position).toString());

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;

        View view = (View) object;
        viewPager.removeView(view);
    }
}
