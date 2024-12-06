package com.example.apptest1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptest1.R;
import com.example.apptest1.adapter.HomeAdapter;
import com.example.apptest1.adapter.PopularAdapter;
import com.example.apptest1.adapter.RecomendedAdapter;
import com.example.apptest1.model.HomeCategory;
import com.example.apptest1.model.PopularModel;
import com.example.apptest1.model.RecomendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class kiHomeFragment extends Fragment {
    RecyclerView popularRec,homeCatRec,recommendedRec;
    PopularAdapter popularAdapter;
    List<PopularModel> popularModelList;
    FirebaseFirestore db;
    //Home category
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    //Recommended san pham
    List<RecomendedModel> recomendedModelList;
    RecomendedAdapter recomendedAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();
        popularRec = root.findViewById(R.id.pop_rec);

        homeCatRec = root.findViewById(R.id.explore_rec);
        //recommended

        recommendedRec = root.findViewById(R.id.recommended_rec);
        //sản phẩm pho bien
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapter);

        db.collection("PupolarProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                            for (QueryDocumentSnapshot document : task.getResult()){
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                            }
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                        }else {
                            Toast.makeText(getActivity(), "erorr"+ task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w("FirestoreTest", "Kết nối Firestore thất bại", task.getException());
                        }
                    }
                });
        //Home category

        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                            for (QueryDocumentSnapshot document : task.getResult()){
                               HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();

                            }
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                        }else {
                            Toast.makeText(getActivity(), "erorr"+ task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w("FirestoreTest", "Kết nối Firestore thất bại", task.getException());
                        }
                    }
                });
//recommended


        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recomendedModelList = new ArrayList<>();
        recomendedAdapter = new RecomendedAdapter(getActivity(),recomendedModelList);
        recommendedRec.setAdapter(recomendedAdapter);

        ////////////////////đề xuất

        db.collection("AllProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                            for (QueryDocumentSnapshot document : task.getResult()){
                                RecomendedModel recomendedModel = document.toObject(RecomendedModel.class);
                                recomendedModelList.add(recomendedModel);
                                recomendedAdapter.notifyDataSetChanged();

                            }
                            Log.d("FirestoreTest", "Kết nối thành công với Firestore");
                        }else {
                            Toast.makeText(getActivity(), "erorr"+ task.getException(), Toast.LENGTH_SHORT).show();
                            Log.w("FirestoreTest", "Kết nối Firestore thất bại", task.getException());
                        }
                    }
                });
///////////////////////////////////////
        return root;
    }
}