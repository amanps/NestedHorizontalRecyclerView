package com.amanps.nestedhorizontalrecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        recyclerview_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(this@MainActivity)
        }

        val sectionedData = listOf(
                SectionModel(HORIZONTAL_LIST, "FIFTIES", listOf(50..59).flatMap { it }),
                SectionModel(HORIZONTAL_LIST, "SIXTIES", listOf(60..69).flatMap { it }),
                SectionModel(HORIZONTAL_LIST, "SEVENTIES", listOf(70..79).flatMap { it }),
                SectionModel(HORIZONTAL_LIST, "EIGHTIES", listOf(80..89).flatMap { it }),
                SectionModel(HORIZONTAL_LIST, "NINETIES", listOf(90..99).flatMap { it })
        )

        (recyclerview_main.adapter as MainAdapter).sections = sectionedData
    }
}
