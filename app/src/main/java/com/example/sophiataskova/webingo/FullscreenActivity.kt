package com.example.sophiataskova.webingo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.find


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)

        mRecyclerView = find(R.id.grid)

        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = GridLayoutManager(this, 5) as RecyclerView.LayoutManager?
        mRecyclerView!!.layoutManager = mLayoutManager

        mAdapter = BingoButtonAdapter(generateRandomBingoCard().toTypedArray())
        mRecyclerView!!.adapter = mAdapter
    }
}
