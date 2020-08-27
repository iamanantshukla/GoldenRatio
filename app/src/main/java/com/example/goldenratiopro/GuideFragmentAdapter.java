package com.example.goldenratiopro;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class GuideFragmentAdapter extends FragmentStateAdapter {

    private static int GUIDE_NO=3;

    public GuideFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return guide_history.newInstance("Page # 1", "history");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return guide_mathematics.newInstance("Page #2", "mathematics");
            case 2:
                return guide_geometry.newInstance("Page #3", "mathematics");
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return GUIDE_NO;
    }
}
