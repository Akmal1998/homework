package com.AkmalProgrammingInc.akmality.Repositories;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.AkmalProgrammingInc.akmality.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ReposView extends Fragment implements ReposContract.IReposView {

    private OnReposViewInteractionListener mListener;

    private static final String ARG_TOKEN = "access_token";
    private String access_token;

    private ListView listView;

    private ReposContract.IReposPresenter presenter;

    private String[] from = {"name", "description"};
    private int[] to = {R.id.repoName, R.id.repoDescription};


    public static ReposView newInstance(String token) {
        ReposView fragment = new ReposView();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TOKEN, token);
        fragment.setArguments(bundle);
       return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ReposPresenter(new ReposModel(), this);
        if (getArguments() != null) {
            access_token = getArguments().getString(ARG_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos_view, container, false);

        listView = view.findViewById(R.id.reposList);

        presenter.loadRepos(access_token);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onReposViewInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnReposViewInteractionListener) {
            mListener = (OnReposViewInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnReposViewInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        presenter.detachView();
        presenter = null;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(ArrayList<HashMap<String, String>> data) {
        listView.setAdapter(new SimpleAdapter(getActivity(), data, R.layout.repo_item, from, to));
    }

    public interface OnReposViewInteractionListener {
        // TODO: Update argument type and name
        void onReposViewInteraction(Uri uri);
    }
}
