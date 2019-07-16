package com.iss.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

public class VoteActivity extends FragmentActivity
    implements PickCandidateFragment.resultInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        replaceFrag(new PickCandidateFragment(), R.id.fragmentSpot);
    }

    @Override
    public void onAttachFragment(Fragment frag) {
        super.onAttachFragment(frag);

        if (frag instanceof PickCandidateFragment) {
            ((PickCandidateFragment)frag).setCallback(this);
        }
    }

    public void replaceFrag(Fragment frag, int where) {
        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        trans.replace(R.id.fragmentSpot, frag);
        trans.commit();
    }

    @Override
    public void onReturnResults(String name, int color) {
        Bundle bundle = new Bundle();
        bundle.putString("candidate", name);
        bundle.putInt("color", color);
        Fragment frag = new YouVoteForFragment();
        frag.setArguments(bundle);

        replaceFrag(frag, R.id.fragmentSpot);
    }
}
