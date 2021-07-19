package com.example.navigationdraweractivity.ui.govscheme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.navigationdraweractivity.R;

public class GovSchemeFragment extends Fragment {

    WebView wv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gov_scheme, container, false);

        wv=(WebView)root.findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://krishijagran.com/agripedia/best-government-schemes-and-programmes-in-agriculture-for-farmers/");
        return root;
    }
}