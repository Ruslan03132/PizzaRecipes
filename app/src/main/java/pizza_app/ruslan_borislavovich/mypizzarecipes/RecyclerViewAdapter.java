package pizza_app.ruslan_borislavovich.mypizzarecipes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private ArrayList<InfoCardItem> cardItems;
    private ArrayList<InfoCardItem> cardItemsFull;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView description;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imagePizza);
            this.title = itemView.findViewById(R.id.pizzaName);
            this.description = itemView.findViewById(R.id.pizzaText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position  = getAdapterPosition();
                    InfoCardItem cardItem = cardItems.get(position);
                    Intent intent = new Intent(context, RecipeActivity.class);
                    intent.putExtra("title" , cardItem.getTitle());
                    intent.putExtra("description" , cardItem.getDescription());
                    intent.putExtra("recipe" , cardItem.getRecipe());
                    intent.putExtra("imageResourse" , cardItem.getImageView());
                    intent.putExtra("adapterPosition" , position);
                    intent.putExtra("context","main");
                    context.startActivity(intent);
                }
            });
        }
    }
    RecyclerViewAdapter(ArrayList<InfoCardItem> cardItems , Context context){
        this.cardItems = cardItems;
        this.context = context;
        this.cardItemsFull = new ArrayList<>(cardItems);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InfoCardItem CardItem = cardItems.get(position);
        holder.imageView.setImageResource(CardItem.getImageView());
        holder.title.setText(CardItem.getTitle());
        holder.description.setText(CardItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<InfoCardItem> filteredList  = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(cardItemsFull);

            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (InfoCardItem item: cardItemsFull){
                    if (item.getTitleString().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results  = new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cardItems.clear();
            cardItems.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

