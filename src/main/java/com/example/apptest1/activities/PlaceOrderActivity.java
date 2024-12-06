package com.example.apptest1.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptest1.DetailActivity;
import com.example.apptest1.MyCartsFragment;
import com.example.apptest1.R;
import com.example.apptest1.model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<MyCartModel> listCart = (ArrayList<MyCartModel>)getIntent().getSerializableExtra("itemList");
        if(listCart!=null && listCart.size()>0){
            for (MyCartModel myCartModel : listCart){

                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("productName",myCartModel.getProductName());
                cartMap.put("productPrice",myCartModel.getProductPrice());
                cartMap.put("currentDate",myCartModel.getCurrentDate());
                cartMap.put("currentTime",myCartModel.getCurrentTime());
                cartMap.put("totalQuantity",myCartModel.getTotalQuantity());
                cartMap.put("totalPrice",myCartModel.getTotalPrice());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlaceOrderActivity.this, "Đã đặt hàng", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }
}