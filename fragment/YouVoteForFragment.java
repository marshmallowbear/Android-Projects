package com.iss.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class YouVoteForFragment extends Fragment {


    public YouVoteForFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_you_vote_for, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String candidate = bundle.getString("candidate");
            int color = bundle.getInt("color");

            Button btn = v.findViewById(R.id.votedFor);
            btn.setText(candidate);
            btn.setBackgroundColor(color);
        }

        return v;
    }

}
