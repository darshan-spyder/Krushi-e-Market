package com.example.navigationdraweractivity.ui.tandc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

import com.example.navigationdraweractivity.R;

public class TandCFragment extends Fragment {

    PDFView mPDFView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tand_c, container, false);

        mPDFView = (PDFView)root.findViewById(R.id.pdfView);
        mPDFView.fromAsset("terms_1.pdf").load();

        return root;
    }
}