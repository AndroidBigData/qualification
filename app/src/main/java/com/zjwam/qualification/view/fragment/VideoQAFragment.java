package com.zjwam.qualification.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.C;
import com.zjwam.qualification.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoQAFragment extends Fragment {

    private Context context;
    private long id;

    public VideoQAFragment() {
        // Required empty public constructor
    }

    public static VideoQAFragment newInstance(Context context,long id) {
        Bundle args = new Bundle();
        args.putLong("id",id);
        VideoQAFragment fragment = new VideoQAFragment();
        fragment.context = context;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_qa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getLong("id");
    }
}
