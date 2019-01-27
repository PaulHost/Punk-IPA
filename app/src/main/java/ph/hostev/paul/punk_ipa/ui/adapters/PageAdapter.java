package ph.hostev.paul.punk_ipa.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ph.hostev.paul.punk_ipa.ui.fragments.BeerListFragment;
import ph.hostev.paul.punk_ipa.ui.fragments.FavariteFragment;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BeerListFragment.newInstance();
            case 1:
                return FavariteFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All"; //todo move to strings resorse
            case 1:
                return "Favorit";
            default:
                return "";
        }
    }
}
