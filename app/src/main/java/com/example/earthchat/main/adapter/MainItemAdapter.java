package com.example.earthchat.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthchat.R;
import com.example.earthchat.dto.OtherUserInfoResponse;

import java.util.List;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ViewHolder> {

    private List<OtherUserInfoResponse> userList;

    public MainItemAdapter(List<OtherUserInfoResponse> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OtherUserInfoResponse userInfo = userList.get(position);
        holder.name.setText("이름  :  " + userInfo.getName());
        holder.age.setText("나이  :  " + String.valueOf(userInfo.getAge()));
        holder.phone.setText("번호  :  " + userInfo.getPhone());
        holder.address.setText("주소  :  " + userInfo.getAddress());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView age;
        public TextView phone;
        public TextView address;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.mainRecyclerName);
            age = itemView.findViewById(R.id.mainRecyclerAge);
            phone = itemView.findViewById(R.id.mainRecyclerphone);
            address = itemView.findViewById(R.id.mainRecycleraddress);
        }
    }
}
