package ex;

// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        expenses = new ArrayList<>();

        
        expenses.add(new Expense("Food", 10.99, new Date()));
        expenses.add(new Expense("Transportation", 5.00, new Date()));
        expenses.add(new Expense("Entertainment", 20.00, new Date()));

        adapter = new ExpenseAdapter(expenses);
    }}