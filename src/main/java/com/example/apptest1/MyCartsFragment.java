package com.example.apptest1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptest1.activities.PlaceOrderActivity;
import com.example.apptest1.adapter.MyCartAdapter;
import com.example.apptest1.model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;
    TextView overTotalAmount;

    FirebaseFirestore db;
    FirebaseAuth auth;

    Button buyNow;


    public MyCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View root = inflater.inflate(R.layout.fragment_my_carts,container,false);
     db = FirebaseFirestore.getInstance();
     auth = FirebaseAuth.getInstance();
     recyclerView = root.findViewById(R.id.recycleview);
     overTotalAmount = root.findViewById(R.id.textView6);
     buyNow = root.findViewById(R.id.buy_now);

     recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
     myCartModelList = new ArrayList<>();
     myCartAdapter = new MyCartAdapter(myCartModelList,getActivity());
     recyclerView.setAdapter(myCartAdapter);

     db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
             .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<QuerySnapshot> task) {
                     if(task.isSuccessful()){
                         for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                             String documentID = documentSnapshot.getId();

                             MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                             cartModel.setDocumentID(documentID);
                             myCartModelList.add(cartModel);
                             myCartAdapter.notifyDataSetChanged();

                         }

                         calculateTotalAmout(myCartModelList);

                     }
                 }
             });
     buyNow.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
             intent.putExtra("itemList", (Serializable) myCartModelList);
             startActivity(intent);
         }
     });



     return root;
    }

    private void calculateTotalAmout(List<MyCartModel> myCartModelList) {
        int totalAmount = 0;

        for (MyCartModel myCartModel : myCartModelList) {
            totalAmount += myCartModel.getTotalPrice();
        }
        overTotalAmount.setText("Tổng tiền: " + totalAmount);
    }

}