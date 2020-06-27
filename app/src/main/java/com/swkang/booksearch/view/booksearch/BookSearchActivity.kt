package com.swkang.booksearch.view.booksearch

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.AppBarLayout
import com.swkang.booksearch.R
import com.swkang.booksearch.base.BaseActivity
import com.swkang.model.domain.booksearch.RequestBookSearchAction
import kotlinx.android.synthetic.main.booksearch_activity.*

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchActivity : BaseActivity() {
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booksearch_activity)
        setSupportActionBar(booksearch_toolbar)

        val navController = findNavController(R.id.fragmentContainer)
        NavigationUI.setupActionBarWithNavController(this, navController)

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            handleIntent(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null) return false
        menu.clear()
        menuInflater.inflate(R.menu.menu_booksearch, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.menu_search).actionView as SearchView)
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            if (!hasQuery()) searchView.onActionViewExpanded()
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp()
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
            appStore.dispatch(RequestBookSearchAction(searchQuery))
            this.query = searchQuery
        }
    }

    fun setUpToolbarTitle(titleStrRes: String) {
        supportActionBar?.setTitle(titleStrRes)
    }

    fun expandToolbar() {
        val appBarLayout = findViewById<AppBarLayout>(R.id.booksearch_appbarlayout)
        appBarLayout.setExpanded(true, false)
    }

    fun hasQuery(): Boolean = !query.isNullOrEmpty()

    fun getQuery(): String? = query

}