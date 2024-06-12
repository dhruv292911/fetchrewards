package com.example.fetchrewards

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RewardsAdapter
    private val dataList: MutableList<Rewards> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RewardsAdapter(dataList)
        recyclerView.adapter = adapter

        fetchData()
    }

    private fun fetchData() {
        val call = ApiClient.apiService.getData()
        call.enqueue(object : Callback<List<Rewards>> {
            override fun onResponse(call: Call<List<Rewards>>, response: Response<List<Rewards>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        // Filter the data where name is not null and not empty
                        val filteredData = data.filter { it.name != null && it.name.isNotEmpty() }

                        // Sort the data by listId and name
                        val sortedData = filteredData.sortedWith(compareBy({ it.listId }, { it.id }))

                        // Log the sorted and filtered data
                        Log.d("MainActivity", "Sorted and Filtered data: $sortedData")

                        dataList.clear()
                        dataList.addAll(sortedData)
                        adapter.notifyDataSetChanged()
                        Log.d("MainActivity", "Data loaded and filtered: ${dataList.size} items")
                    } else {
                        Log.e("MainActivity", "Response body is null")
                    }
                } else {
                    Log.e("MainActivity", "Response not successful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Rewards>>, t: Throwable) {
                Log.e("MainActivity", "Network error: ${t.message}")
            }
        })
    }


}
