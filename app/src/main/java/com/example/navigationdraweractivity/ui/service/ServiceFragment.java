package com.example.navigationdraweractivity.ui.service;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.github.barteksc.pdfviewer.PDFView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationdraweractivity.R;

public class ServiceFragment extends Fragment {

    PDFView mPDFView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_service, container, false);

        mPDFView = (PDFView)root.findViewById(R.id.pdfView);
        mPDFView.fromAsset("service_1.pdf").load();

        return root;
    }
}