package com.zjwam.qualification.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zjwam.qualification.R;
import com.zjwam.qualification.bean.MineBean;
import com.zjwam.qualification.presenter.MinePresenter;
import com.zjwam.qualification.presenter.ipresenter.IMinePresenter;
import com.zjwam.qualification.utils.GlideImageUtil;
import com.zjwam.qualification.utils.RequestOptionsUtils;
import com.zjwam.qualification.view.activity.AboutActivity;
import com.zjwam.qualification.view.activity.MineCommnetActivity;
import com.zjwam.qualification.view.activity.MineNoteActivity;
import com.zjwam.qualification.view.activity.MineQAActivity;
import com.zjwam.qualification.view.activity.MineSetActivity;
import com.zjwam.qualification.view.iview.IMineView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements IMineView {
    private Context context;
    private ImageView mine_tx;
    private TextView mine_nickname, mine_gy;
    private IMinePresenter minePresenter;
    private RelativeLayout mine_note,mine_answer,mine_comment,mine_info,mine_about,mine_set;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance(Context context) {
        MineFragment fragment = new MineFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        minePresenter = new MinePresenter(context, this);
        minePresenter.getMine();
        mine_note.setOnClickListener(onClickListener);
        mine_answer.setOnClickListener(onClickListener);
        mine_comment.setOnClickListener(onClickListener);
        mine_info.setOnClickListener(onClickListener);
        mine_about.setOnClickListener(onClickListener);
        mine_set.setOnClickListener(onClickListener);
    }

    private void initView() {
        mine_tx = getActivity().findViewById(R.id.mine_tx);
        mine_nickname = getActivity().findViewById(R.id.mine_nickname);
        mine_gy = getActivity().findViewById(R.id.mine_gy);
        mine_note = getActivity().findViewById(R.id.mine_note);
        mine_answer = getActivity().findViewById(R.id.mine_answer);
        mine_comment = getActivity().findViewById(R.id.mine_comment);
        mine_info = getActivity().findViewById(R.id.mine_info);
        mine_about = getActivity().findViewById(R.id.mine_about);
        mine_set = getActivity().findViewById(R.id.mine_set);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.mine_note:
                    startActivity(new Intent(getActivity(), MineNoteActivity.class));
                    break;
                case R.id.mine_answer:
                    startActivity(new Intent(getActivity(), MineQAActivity.class));
                    break;
                case R.id.mine_comment:
                    startActivity(new Intent(getActivity(), MineCommnetActivity.class));
                    break;
                case R.id.mine_info:
                    break;
                case R.id.mine_about:
                    startActivity(new Intent(getActivity(), AboutActivity.class));
                    break;
                case R.id.mine_set:
                    startActivity(new Intent(getActivity(), MineSetActivity.class));
                    break;
            }
        }
    };

    @Override
    public void setMine(MineBean mineBean) {
        GlideImageUtil.setImageView(context, mineBean.getPic(), mine_tx, RequestOptionsUtils.circleTransform());
        mine_nickname.setText(mineBean.getNickname());
        mine_gy.setText(mineBean.getSign());
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
