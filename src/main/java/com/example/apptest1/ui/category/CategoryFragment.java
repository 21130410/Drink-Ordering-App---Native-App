package com.example.apptest1.ui.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptest1.R;
import com.example.apptest1.adapter.NavCategoryAdapter;
import com.example.apptest1.adapter.RecomendedAdapter;
import com.example.apptest1.model.NavCategoryModel;
import com.example.apptest1.model.RecomendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//import com.example.apptest1.databinding.FragmentGalleryBinding;

public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    List<NavCategoryModel> navCategoryModelList;
    NavCategoryAdapter navCategoryAdapter;
    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category,container,false);
        db = FirebaseFirestore.getInstance();

        recyclerView = root.findViewById(R.id.cat_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        navCategoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(),navCategoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                            for (QueryDocumentSnapshot document : task.getResult()){
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                navCategoryModelList.add(navCategoryModel);
                                navCategoryAdapter.notifyDataSetChanged();

                            }
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                        }else {
                            Toast.makeText(getActivity(), "erorr"+ task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w("FirestoreTest", "Kết nối Firestore thất bại", task.getException());
                        }
                    }
                });
        return root;
    }

}