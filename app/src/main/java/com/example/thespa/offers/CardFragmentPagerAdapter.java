package com.example.thespa.offers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.example.thespa.models.Offer;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {
    private List<OfferCardFragment> fragments;
    private float baseElevation;

    private ArrayList<Offer> offers;
    private Offer offer;

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation, ArrayList<Offer> offers) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        this.offers=offers;
        for(int i = 0; i<offers.size() ; i++){

            addCardFragment(new OfferCardFragment());
        }
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        offer=offers.get(position);
        return OfferCardFragment.getInstance(offer);
    }



    /**
     * instantiate CardFragment
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (OfferCardFragment) fragment);
        return fragment;
    }

    /**
     * to add fragment to View
     */
    public void addCardFragment(OfferCardFragment fragment) {
        fragments.add(fragment);
    }
    {}
}
