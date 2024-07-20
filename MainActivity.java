package qwe;

import android.app.Activity;
import android.os.Bundle;

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var recipeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        recipeRecyclerView = findViewById(R.id.recipeRecyclerView)

        // Set up RecyclerView adapter and layout manager
        val recyclerViewAdapter = RecipeAdapter()
        recipeRecyclerView.adapter = recyclerViewAdapter
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)

        // Add search functionality
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText.text.toString()
                // Make API request to search for recipes
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .build()
                val apiService = retrofit.create(ApiService::class.java)
                val call = apiService.searchRecipes(ApiConstants.API_KEY, query)
                call.enqueue(object : Callback<RecipeResponse> {
                    override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                        val recipes = response.body()?.results
                        recyclerViewAdapter.submitList(recipes)
                    }

                    override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                        // Handle error
                    }
                })
            }
            true
        }
    }
}
class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    private val recipes = mutableListOf<Recipe>()

    fun submitList(recipes: List<Recipe>?) {
        this.recipes.clear()
        if (recipes != null) {
            this.recipes.addAll(recipes)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(recipe: Recipe) {
        // Bind recipe data to views
    }
}