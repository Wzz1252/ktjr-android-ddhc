package com.torment.lib.ui.impl;

import com.torment.lib.ui.entity.tab.TabEntity;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 提供好的用于切换Fragment的类
 * Created by Torment on 2016/9/28.
 */
public class IFragmentTab implements OnTabClickListener {
    private FragmentManager mFragmentManager;
    private int mFragmentId;

    public IFragmentTab(FragmentManager fragmentManager, int fragmentId) {
        this.mFragmentManager = fragmentManager;
        this.mFragmentId = fragmentId;
    }

    @Override
    public void tabClick(TabEntity tab, int postion) {
        try {
            FragmentManager fragmentManager = mFragmentManager;

            String simpleName = tab.callBack.getSimpleName();
            Fragment showFragment = fragmentManager.findFragmentByTag(simpleName);
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            List<Fragment> fragments = mFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                Fragment f = fragments.get(i);
                if (!simpleName.equals(f.getTag())) {
                    transaction.hide(f);
                }
            }

            if (showFragment == null) {
                showFragment = tab.callBack.newInstance().getFragment();
                transaction.add(mFragmentId, showFragment, simpleName);
            } else {
                transaction.show(showFragment);
            }
            transaction.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
