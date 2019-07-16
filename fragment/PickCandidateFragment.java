package com.iss.fragment;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PickCandidateFragment extends Fragment
    implements View.OnClickListener {

    PickCandidateFragment.resultInterface callback;

    public PickCandidateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pickcandidate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        for (int i=0; i<4; i++) {
            int id = getResources().getIdentifier("btn" + i,
                    "id", getActivity().getPackageName());
            Button btn = view.findViewById(id);
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        ColorDrawable bkgd = (ColorDrawable) btn.getBackground();
        int color = bkgd.getColor();
        String candidate = btn.getText().toString();

        callback.onReturnResults(candidate, color);
    }

    public void setCallback(PickCandidateFragment.resultInterface callback) {
        this.callback = callback;
    }

    public interface resultInterface {
        void onReturnResults(String name, int color);
    }
}
