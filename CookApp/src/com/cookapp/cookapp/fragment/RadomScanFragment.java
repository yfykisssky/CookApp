package com.cookapp.cookapp.fragment;

import com.cookapp.cookapp.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RadomScanFragment extends Fragment {
	
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.fragment_random_scan,null);

		return view;
	}
}
