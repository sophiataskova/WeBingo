package com.example.sophiataskova.webingo

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.sophiataskova.webingo.FullscreenActivity.Companion.currentNumber
import com.example.sophiataskova.webingo.FullscreenActivity.Companion.selectedBingoNumbers


class BingoButtonAdapter() : Adapter<BingoButtonAdapter.ViewHolder>() {
    private var mDataset: Array<Int>? = null

    constructor(dataset: Array<Int>) : this() {
        this.mDataset = dataset
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.mTextView?.text = mDataset?.get(position).toString()
        holder?.mTextView?.setOnClickListener {
            if (currentNumber.equals(Integer.parseInt(holder?.mTextView?.text as String))) {
                holder?.mTextView?.setBackgroundResource(R.drawable.red_circle_w_inner_circle)
                selectedBingoNumbers = selectedBingoNumbers.plusElement(currentNumber)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataset?.size as Int
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.bingo_number, parent, false) as TextView
        val vh = ViewHolder(v)
        return vh
    }

    inner class ViewHolder(var mTextView: TextView) : RecyclerView.ViewHolder(mTextView)
}