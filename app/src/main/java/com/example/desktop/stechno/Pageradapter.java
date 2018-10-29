package com.example.desktop.stechno;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pageradapter extends FragmentStatePagerAdapter {

    int noftab;
    public Pageradapter(FragmentManager fm, int Numtabss){
        super(fm);
        this.noftab = Numtabss;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                BlankFragment1 blankFragment1 = new BlankFragment1();
                return blankFragment1;
            case 1:
                BlankFragment2 blankFragment2 = new BlankFragment2();
                return blankFragment2;
            case 2:
                BlankFragment3 blankFragment3 = new BlankFragment3();
                return blankFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noftab;
    }
}
