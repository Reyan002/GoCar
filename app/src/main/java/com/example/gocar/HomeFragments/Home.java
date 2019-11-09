package com.example.gocar.HomeFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocar.Adapters.MycarsAdapter;
import com.example.gocar.Classes.Cars;
import com.example.gocar.R;
import com.example.gocar.SessionManager.SessionManager;

import java.util.ArrayList;

public class Home extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private MycarsAdapter mycarsAdapter;
    private ArrayList<Cars> myListcar;
    private RecyclerView.LayoutManager layoutManager;
    private SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Root= inflater.inflate(R.layout.home_fragment,container,false);


        Cars cars = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars1 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars2 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars3 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars4 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars5 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars6 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars7 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars8 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        Cars cars9 = new Cars(1500,"Suzuki Mehran","UP mor. Karachi");
        myListcar=new ArrayList<>();
        myListcar.add(cars);
        myListcar.add(cars1);
        myListcar.add(cars2);
        myListcar.add(cars3);
        myListcar.add(cars4);
        myListcar.add(cars5);
        myListcar.add(cars6);
        myListcar.add(cars7);
        myListcar.add(cars8);
        myListcar.add(cars9);


        recyclerView = Root.findViewById(R.id.recyclerview);
        //layoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mycarsAdapter=new MycarsAdapter(myListcar,context);
        recyclerView.setAdapter(mycarsAdapter);


        return Root;
    }
}
