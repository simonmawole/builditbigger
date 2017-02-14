package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.TellJokes;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    InterstitialAd mInterstitialAd;
    private ProgressDialog progressDialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        getActivity().setTitle("Built It Bigger (FREE)");

        progressDialog = new ProgressDialog(getActivity());

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("ABCDEF012345")
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                tellJoke();
            }
        });

        requestNewInterstitial();

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

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            loadJokes();
        }
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
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
