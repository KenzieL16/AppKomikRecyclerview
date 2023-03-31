package com.kenzie.komikapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvKomik: RecyclerView
    private var list: ArrayList<Komik> = arrayListOf()
    private var title: String = "Simple Mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvKomik = findViewById(R.id.rv_komik)
        rvKomik.setHasFixedSize(true)
        list.addAll(KomikData.listData)

        showRecyclerGrid()
    }
    private fun showRecyclerList() {
        rvKomik.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListKomik(list)
        rvKomik.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListKomik.OnItemClickCallback {
            override fun onItemClicked(data: Komik) {
                showSelectedKomik(data)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "List Mode"
                showRecyclerList()
                setActionBarTitle(title)
            }

            R.id.action_grid -> {
                title = "Simple Mode"
                showRecyclerGrid()
                setActionBarTitle(title)
            }

            R.id.action_cardview -> {
                title = "Card View Mode"
                showRecyclerCardView()
                setActionBarTitle(title)
            }
        }

    }
    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
    }
}
    private fun showRecyclerGrid() {
        rvKomik.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridKomik(list)
        rvKomik.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridKomik.OnItemClickCallback {
            override fun onItemClicked(data: Komik) {
                showSelectedKomik(data)
            }
        })

    }
    private fun showRecyclerCardView() {
        rvKomik.layoutManager = LinearLayoutManager(this)
        val cardViewKomik = CardViewKomik(list)
        rvKomik.adapter = cardViewKomik
    }
    private fun showSelectedKomik(komik: Komik) {
        Toast.makeText(this, "Kamu memilih " + komik.name, Toast.LENGTH_SHORT).show()
    }

}