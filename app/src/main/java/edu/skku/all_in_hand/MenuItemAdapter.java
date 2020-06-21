package edu.skku.all_in_hand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    private ArrayList<MenuItem> menuItems = null ;
    private OnItemClickListener mListener = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuname, menuprice, menudesc;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            menuname = itemView.findViewById(R.id.menuname);
            menuprice = itemView.findViewById(R.id.menuprice);
            menudesc = itemView.findViewById(R.id.menudesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        if(mListener !=null){
                            mListener.onItemClick(v,position);
                        }
                    }

                }
            });
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    MenuItemAdapter(ArrayList<MenuItem> menulist) {
        menuItems = menulist ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public MenuItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_menuitem, parent, false) ;
        MenuItemAdapter.ViewHolder vh = new MenuItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MenuItemAdapter.ViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.menudesc.setText(item.getDesc());
        holder.menuprice.setText(item.getPrice());
        holder.menuname.setText(item.getName());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return menuItems.size() ;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    // OnItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }
}

