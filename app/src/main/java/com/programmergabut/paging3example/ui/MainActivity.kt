package com.programmergabut.paging3example.ui

import android.os.Bundle
import android.widget.Toast
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmergabut.paging3example.R
import com.programmergabut.paging3example.base.BaseActivity
import com.programmergabut.paging3example.databinding.ActivityMainBinding
import com.programmergabut.paging3example.ui.adapter.FooterAdapter
import com.programmergabut.paging3example.ui.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    @Inject
    lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUsersRecyclerView()
        viewModel.searchPhoto("jiwo")
    }

    private fun setupUsersRecyclerView() {
        binding.rvGithubUser.apply {
            adapter = usersAdapter.withLoadStateHeaderAndFooter(
                footer = FooterAdapter({ usersAdapter.retry() }, { loadState ->
                    Toast.makeText(this@MainActivity, (loadState as LoadState.Error).error.message, Toast.LENGTH_SHORT ).show()
                }) ,
                header = FooterAdapter({ usersAdapter.retry() }, { loadState ->
                    Toast.makeText(this@MainActivity, (loadState as LoadState.Error).error.message, Toast.LENGTH_SHORT ).show()
                })
            )
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun setListener() {
        viewModel.users.observe(this, {
            usersAdapter.submitData(lifecycle, it)
        })

        usersAdapter.addLoadStateListener { combinedLoadStates  ->
            if(combinedLoadStates.source.refresh is LoadState.NotLoading &&
                combinedLoadStates.append.endOfPaginationReached &&
                usersAdapter.itemCount < 1){
                //hide component
                Toast.makeText(this, "Query NOT FOUND", Toast.LENGTH_SHORT).show()
            } else {
                //show component
            }

            when(combinedLoadStates.source.refresh){
                is LoadState.Loading -> {
                    showLoading(true)
                }
                is LoadState.NotLoading -> {
                    showLoading(false)
                }
                is LoadState.Error -> {
                    val loadState = combinedLoadStates.source.refresh as LoadState.Error
                    Toast.makeText(this, loadState.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}