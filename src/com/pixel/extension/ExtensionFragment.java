package com.pixel.extension;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceScreen;
import android.content.Context;
import android.content.ContentResolver;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.content.Context;
import android.content.ContentResolver;
import android.content.SharedPreferences;


import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.pixel.extension.fragments.Lockscreen;
import com.pixel.extension.fragments.Navigation;
import com.pixel.extension.fragments.QuickSettings;
import com.pixel.extension.fragments.Recents;
import com.pixel.extension.fragments.Statusbar;
import com.pixel.extension.fragments.System;
import com.pixel.extension.views.MenuViews;

public class ExtensionFragment extends SettingsPreferenceFragment implements View.OnClickListener, Preference.OnPreferenceChangeListener {

    private static final String TAG = "extension";

    private MenuViews statusbar,navbar,qs,recents,lockscreen,system;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.extension_fragment, container, false);
        mFragmentManager = getActivity().getSupportFragmentManager();
        initViews(view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getActivity().setTitle(R.string.extension_title);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void initViews(final View view) {
        statusbar = (MenuViews) view.findViewById(R.id.statusbar_menu);
        navbar = (MenuViews) view.findViewById(R.id.navbar_menu);
        qs = (MenuViews) view.findViewById(R.id.quick_settings_menu);
        recents = (MenuViews) view.findViewById(R.id.recent_menu);
        lockscreen = (MenuViews) view.findViewById(R.id.lockscreen_menu);
        system = (MenuViews) view.findViewById(R.id.system_menu);

        initClick();
    }

    private void initClick() {
        statusbar.setOnClickListener(this);
        navbar.setOnClickListener(this);
        qs.setOnClickListener(this);
        recents.setOnClickListener(this);
        lockscreen.setOnClickListener(this);
        system.setOnClickListener(this);
    }

    private void loadFragment(String tag, boolean addToStack, Bundle bundle, Fragment setFragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragment = setFragment;

        if (addToStack) {
            if (bundle != null)
                mFragment.setArguments(bundle);
            mFragmentTransaction.replace(R.id.fragment_container, mFragment, tag);
            mFragmentTransaction.addToBackStack(tag).commit();

        } else {
            if (bundle != null)
                mFragment.setArguments(bundle);
            mFragmentTransaction.replace(R.id.fragment_container, mFragment, tag);
            mFragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.statusbar_menu:
                loadFragment(Constants.STATUS_BAR_MENU_FRAGMENT,true,null,new Statusbar());
                break;
            case  R.id.navbar_menu:
                loadFragment(Constants.NAV_BAR_MENU_FRAGMENT,true,null,new Navigation());
                break;
            case  R.id.quick_settings_menu:
                loadFragment(Constants.QUICK_SETTINGS_MENU_FRAGMENT,true,null,new QuickSettings());
                break;
            case  R.id.recent_menu:
                loadFragment(Constants.RECENT_MENU_FRAGMENT,true,null,new Recents());
                break;
            case  R.id.lockscreen_menu:
                loadFragment(Constants.LOCK_SCREEN_MENU_FRAGMENT,true,null,new Lockscreen());
                break;
            case  R.id.system_menu:
                loadFragment(Constants.SYSTEM_MENU_FRAGMENT,true,null,new System());
                break;

        }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.EXTENSION;
    }

     @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
        return true;
    }

}
