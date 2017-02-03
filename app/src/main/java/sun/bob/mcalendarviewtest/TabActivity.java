package sun.bob.mcalendarviewtest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import sun.bob.mcalendarview.CellConfig;
import sun.bob.mcalendarview.listeners.OnExpDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthScrollListener;
import sun.bob.mcalendarview.views.ExpCalendarView;

public class TabActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private TextView mPageTitleTv;

        private TextView YearMonthTv;
        private ExpCalendarView expCalendarView;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);

            mPageTitleTv = (TextView) rootView.findViewById(R.id.tab_activity_page_title);
            mPageTitleTv.setText("Page: "+getArguments().getInt(ARG_SECTION_NUMBER)+"　　在此滑动page，避免冲突");

            expCalendarView = ((ExpCalendarView) rootView.findViewById(R.id.calendar_exp));
            YearMonthTv = (TextView) rootView.findViewById(R.id.main_YYMM_Tv);
            YearMonthTv.setText(Calendar.getInstance().get(Calendar.YEAR) + "年" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月");
            expCalendarView.setOnDateClickListener(new OnExpDateClickListener()).setOnMonthScrollListener(new OnMonthScrollListener() {
                @Override
                public void onMonthChange(int year, int month) {
                    YearMonthTv.setText(String.format("%d年%d月", year, month));
                }

                @Override
                public void onMonthScroll(float positionOffset) {
//                Log.i("listener", "onMonthScroll:" + positionOffset);
                }
            });

            imageInit(rootView);

            expCalendarView.markDate(2016, 10, getArguments().getInt(ARG_SECTION_NUMBER));

            return rootView;
        }


        private boolean ifExpand = true;

        private void imageInit(View view) {
            final ImageView expandIV = (ImageView) view.findViewById(R.id.main_expandIV);
            expandIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ifExpand) {
                        CellConfig.Month2WeekPos = CellConfig.middlePosition;
                        CellConfig.ifMonth = false;
                        expandIV.setImageResource(R.mipmap.icon_arrow_down);
                        expCalendarView.shrink();
                    } else {
                        CellConfig.Week2MonthPos = CellConfig.middlePosition;
                        CellConfig.ifMonth = true;
                        expandIV.setImageResource(R.mipmap.icon_arrow_up);
                        expCalendarView.expand();
                    }
                    ifExpand = !ifExpand;
                }
            });
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
