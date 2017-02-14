package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.TellJokes;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressDialog progressDialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        getActivity().setTitle("Built It Bigger (PAID)");

        progressDialog = new ProgressDialog(getActivity());

        Button bnTellJoke = (Button) root.findViewById(R.id.bnTellJoke);
        bnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke() {
        loadJokes();
    }

    public void loadJokes(){
        TellJokes jokes = new TellJokes();
        //Toast.makeText(this, jokes.getTheJoke(), Toast.LENGTH_SHORT).show();

        //Intent intent = new Intent(this, JokesDisplayActivity.class);
        //intent.putExtra(JokesDisplayActivity.JOKES_KEY, jokes.getTheJoke());
        //startActivity(intent);
        showProgressDialog();
        new EndpointsAsyncTask(progressDialog).execute(new Pair<Context, String>(getActivity(), jokes.getTheJoke()));
    }

    public void showProgressDialog(){
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    public void hideProgressDialog(){
        if(progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }


}
