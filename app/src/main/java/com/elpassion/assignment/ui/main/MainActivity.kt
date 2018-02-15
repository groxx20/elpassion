package com.elpassion.assignment.ui.main

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
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


class MainActivity : AppCompatActivity() , MainView{


    private lateinit var mainComponent: MainComponent

    @Inject
    lateinit var mainPresenter: MainPresenterImpl

    private var itemsAdapter: ItemsAdapter? = null

    private var items: ArrayList<ItemList> = ArrayList()

    private var query:String = ""

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
    private fun injectDependecies(){

        val appComponent = PassionApp.appComponent
        mainComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .appComponent(appComponent)
                .build()
        mainComponent.inject(this)
    }

    override fun showLoading() { progressBarDetail.visibility = View.VISIBLE }

    override fun hideLoading() { progressBarDetail.visibility = View.INVISIBLE }

    override fun onFailure(msg: String) {
        toastError(msg)
    }

    /**
     *  Move forward
     */
    override fun goNext(name:String) {

        hideKeyboard()
        goToActivity<DetailActivity> { putExtra("user", name) }
    }

    /**
     *  Fill recyclerview with items
     */
    override fun showStuff(items : List<ItemList>) {

        configVIew(items)
        itemsAdapter = ItemsAdapter( items, this, this)
        rvItems.adapter = itemsAdapter
    }


    override fun getItemsUser() {
        mainPresenter.getUsers(query)
    }

    override fun getItemsRepos() {

        mainPresenter.getRepos(query)
    }


    override fun readyToSortItems(items: List<ItemList>) {

        mainPresenter.sortList(items)
    }

    /**
     *  Layout manager for RecyclerView
     */
    private fun setupRecyclerView(recyclerView: RecyclerView) {

        rvItems.isNestedScrollingEnabled

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

    }

    private fun configVIew(items: List<ItemList>){

            if(items.isEmpty()){
                arrow.visibility = View.VISIBLE
                searchTxt.visibility = View.VISIBLE
                arrow.setColorFilter(Color.WHITE)
            }else{
                arrow.visibility = View.INVISIBLE
                searchTxt.visibility = View.INVISIBLE
                arrow.setColorFilter(Color.WHITE)
            }
    }

    /**
     *  Search city
     */
    private fun listenSearch(){

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(querySearch: String): Boolean {
                query = querySearch
                mainPresenter.getUsers(query)
                return true
            }

            override fun onQueryTextChange(queryString: String): Boolean {

                query = queryString
                return true
            }
        })
    }
}
