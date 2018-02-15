package com.elpassion.assignment.ui.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.elpassion.assignment.PassionApp
import com.elpassion.assignment.R
import com.elpassion.assignment.adapter.ItemsAdapter
import com.elpassion.assignment.model.ItemList
import com.elpassion.assignment.ui.detail.DetailActivity
import com.elpassion.assignment.ui.main.di.components.DaggerMainComponent
import com.elpassion.assignment.ui.main.di.components.MainComponent
import com.elpassion.assignment.ui.main.di.modules.MainModule
import com.elpassion.assignment.utils.goToActivity
import com.elpassion.assignment.utils.hideKeyboard
import com.elpassion.assignment.utils.toastError
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {


    private lateinit var mainComponent: MainComponent

    @Inject
    lateinit var mainPresenter: MainPresenterImpl

    private var itemsAdapter: ItemsAdapter? = null

    private var items: ArrayList<ItemList> = ArrayList()

    private var query: String = ""

    private var page = 0


    private var layoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependecies()
        setupRecyclerView(rvItems)
        listenSearch()
        configVIew(items)

    }

    /**
     *  Inject dependecies
     */
    private fun injectDependecies() {

        val appComponent = PassionApp.appComponent
        mainComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .appComponent(appComponent)
                .build()
        mainComponent.inject(this)
    }

    /**
     *  Setup paginated call for Endpoint
     */
    private fun setupListener() {


        rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val counter = itemsAdapter!!.itemCount - 1


                val lastVisibleItem = layoutManager!!.findLastVisibleItemPosition()
                if (lastVisibleItem == counter) {

                        page++
                        mainPresenter.getUsers(page, query)
                }

            }
        })
    }

    override fun showLoading() {
        progressBarDetail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarDetail.visibility = View.INVISIBLE
    }

    override fun onFailure(msg: String) {
        toastError(msg)
    }

    /**
     *  Move forward
     */
    override fun goNext(name: String) {

        hideKeyboard()
        goToActivity<DetailActivity> { putExtra("name", name) }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    /**
     *  Fill recyclerview with items
     */
    override fun showStuff(items: List<ItemList>) {

        configVIew(items)
        itemsAdapter = ItemsAdapter(items, this, this)
        rvItems.adapter = itemsAdapter

        setupListener()
    }


    /**
     *  Get repositories from Endpoint
     */

    override fun getItemsRepos() { mainPresenter.getRepos(page,query) }

    /**
     *  Sort Items by ID
     */

    override fun readyToSortItems(items: List<ItemList>) { mainPresenter.sortList(items) }

    /**
     *  Layout manager for RecyclerView
     */
    private fun setupRecyclerView(recyclerView: RecyclerView) {

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

    }

    /**
     *  Method to show empty state of list
     */
    private fun configVIew(items: List<ItemList>) {

        if (items.isEmpty()) {
            arrow.visibility = View.VISIBLE
            searchTxt.visibility = View.VISIBLE
            arrow.setColorFilter(Color.WHITE)
        } else {
            arrow.visibility = View.INVISIBLE
            searchTxt.visibility = View.INVISIBLE
            arrow.setColorFilter(Color.WHITE)
        }
    }

    /**
     *  Search users and repos
     */
    private fun listenSearch() {

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(querySearch: String): Boolean {
                items.clear()
                query = querySearch
                mainPresenter.getUsers(page,query)
                val inputManager:InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
                return true
            }

            override fun onQueryTextChange(queryString: String): Boolean {
                query = queryString
                return true
            }
        })

    }
}
