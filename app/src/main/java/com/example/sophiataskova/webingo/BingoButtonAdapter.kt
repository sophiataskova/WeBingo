package com.example.sophiataskova.webingo

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView






class BingoButtonAdapter() : Adapter<BingoButtonAdapter.ViewHolder>() {
    private var mDataset: Array<String>? = null

    constructor(dataset: Array<String>) : this() {
        this.mDataset = dataset
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.mTextView.text = mDataset!![position];
    }

    override fun getItemCount(): Int {
        return mDataset!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.bingo_number, parent, false) as TextView
        val vh = ViewHolder(v)
        return vh
    }

    inner class ViewHolder(var mTextView: TextView) : RecyclerView.ViewHolder(mTextView)
}