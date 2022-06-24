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

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {
    private final IncomeAdapterEventListener eventListener;
    private List<Movements> movementList;

    public IncomeAdapter(IncomeAdapterEventListener eventListener){
        this.eventListener = eventListener;
        this.movementList = new ArrayList<>();
    }

    @NonNull
    @Override
    public IncomeAdapter.IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_row, parent, false);
        return new IncomeViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.IncomeViewHolder holder, int position) {
        Movements movements =this.movementList.get(position);

        holder.setDescription(movements.getDescription());
        holder.setMovementsDate(movements.getMovementsDate());
        holder.setMovementsvalue(movements.getValue());
        holder.setMovementsCategoryId(movements.getIdCategory());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onIncomeClicked(movements.getIdMovements());
            }

        });
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                eventListener.onIncomeLongClicked(movements.getIdMovements());
                return true;
            }
        });
    }

    public int getItemCount(){
        return this.movementList.size();
    }
    public void updateIncomeList(List<Movements> allMovements){
        this.movementList = allMovements;
        notifyDataSetChanged();
    }
    public static class IncomeViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView description;
        private TextView categoryName;
        private TextView movementsDate;
        private TextView movementsvalue;
        private Context context;
        private static SimpleDateFormat dates= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        public IncomeViewHolder(@NonNull View rootView, Context context){
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.description = itemView.findViewById(R.id.incomeDescription);
            this.movementsDate = itemView.findViewById(R.id.incomeDate);
            this.movementsvalue = itemView.findViewById(R.id.incomePrice);
            this.categoryName = itemView.findViewById(R.id.categoryName);

        }
        public void setDescription(String description){
            this.description.setText(description);
        }
        public void setMovementsDate(long movementsDate){
            Date date = new Date(movementsDate);
            this.movementsDate.setText(dates.format(date));
        }
        public void setMovementsvalue(int movementsvalue){
            this.movementsvalue.setText(movementsvalue);
        }

        public void setMovementsCategoryId(long categoryId){
            Category category = Database.getInstance(context).getcategoryDAO().getById(categoryId);
            this.categoryName.setText(category.getCategoryName());
        }
    }
    public interface IncomeAdapterEventListener{
        void onIncomeClicked(long chatsId);
        void onIncomeLongClicked(long chatsId);
    }
}
