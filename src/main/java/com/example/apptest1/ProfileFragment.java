package com.example.apptest1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import com.example.apptest1.databinding.FragmentSlideshowBinding;

public class ProfileFragment extends Fragment {

    public class CategoryFragment extends Fragment {

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_profile,container,false);
            return root;
        }

    }
}