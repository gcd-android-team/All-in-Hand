package edu.skku.all_in_hand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuOptionAdapter extends RecyclerView.Adapter<MenuOptionAdapter.ViewHolder> {

    private ArrayList<MenuOption> menuOptions = null;
    private MenuItemAdapter.OnItemClickListener mListener = null;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            option = itemView.findViewById(R.id.option);
        }
    }

    MenuOptionAdapter(ArrayList<MenuOption> optionlist) {
        menuOptions = optionlist ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_menuoptions, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuOptionAdapter.ViewHolder holder, int position) {
        MenuOption item = menuOptions.get(position);
        holder.option.setText(item.getName()+" (+"+item.getPrice()+"원)");
    }

    @Override
    public int getItemCount() {
        return menuOptions.size();
    }


}
