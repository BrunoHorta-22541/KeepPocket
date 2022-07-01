package pt.estig.twdm.pdm.keep_pocket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {
    private final MainActivityAdapter.MainActivityAdapterEventListener eventListener;
    private List<Movements> movementList;
    private List<Category> categoryList;
    private long userId;

    public MainActivityAdapter(MainActivityAdapter.MainActivityAdapterEventListener eventListener){
        this.eventListener = eventListener;
        this.movementList = new ArrayList<>();
        this.categoryList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MainActivityAdapter.MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_spends_row, parent, false);
        return new MainActivityAdapter.MainActivityViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.MainActivityViewHolder holder, int position) {
        Movements movements =this.movementList.get(position);
        Category category = this.categoryList.get(position);
        Session activeSession = SessionManager.getActiveSession(holder.context);
        userId = activeSession.getUserid();
        holder.setCategoryName(category.getCategoryName());
        holder.setTotalExpenses(movements.getIdCategory());


        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onCategoryMainClicked(category.getIdCategory());
            }

        });
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                eventListener.onCategoryMainLongClicked(category.getIdCategory());
                return true;
            }
        });
    }

    public int getItemCount(){
        return this.movementList.size();
    }
    public void updateCategoryMainList(List<Movements> allMovements){
        this.movementList = allMovements;
        notifyDataSetChanged();
    }

    public static class MainActivityViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView totalExpenses;
        private TextView categoryName;
        private Context context;

        public MainActivityViewHolder(@NonNull View rootView, Context context){
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.totalExpenses = itemView.findViewById(R.id.textViewTotalExpenses);
            this.categoryName = itemView.findViewById(R.id.textViewCategoryMain);

        }

        public void setTotalExpenses(long userId) {
            List<Movements> movementsList = Database.getInstance(context).getmovementsDAO().getTotalByUserId(userId);
            this.totalExpenses.setText(movementsList.toString());
        }

        public void setCategoryName(String categoryName) {
            this.categoryName.setText(categoryName);
        }
    }


    public interface MainActivityAdapterEventListener{
        void onCategoryMainClicked(long categoryId);
        void onCategoryMainLongClicked(long categoryId);
    }
}
