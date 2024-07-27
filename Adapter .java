
import android.app.Activity;
import android.os.Bundle;

// ExpenseAdapter.java
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenses;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.categoryTextView.setText(expense.getCategory());
        holder.amountTextView.setText(String.valueOf(expense.getAmount()));
        holder.dateTextView.setText(expense.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public TextView amountTextView;
        public TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
            amountTextView = itemView.findViewById(R.id.amount_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }
    }
}