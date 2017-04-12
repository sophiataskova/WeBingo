package com.example.sophiataskova.webingo

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import android.widget.TextView
import com.example.sophiataskova.webingo.FullscreenActivity.Companion.currentNumber
import com.example.sophiataskova.webingo.FullscreenActivity.Companion.selectedBingoNumbers


class BingoButtonAdapter() : Adapter {
    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasStableIds(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewTypeCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mDataset: Array<Int>? = null

    constructor(dataset: Array<Int>) : this() {
        this.mDataset = dataset
    }



//    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
//        holder?.mTextView?.text = mDataset?.get(position).toString()
//        holder?.mTextView?.setOnClickListener {
//            if (currentNumber.equals(Integer.parseInt(holder?.mTextView?.text as String))) {
//                holder?.mTextView?.setBackgroundResource(R.drawable.red_circle_w_inner_circle)
//                selectedBingoNumbers = selectedBingoNumbers.plusElement(currentNumber)
//            }
//        }
//    }

    override fun getItemCount(): Int {
        return mDataset?.size as Int
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.bingo_number, parent, false) as TextView
        val vh = ViewHolder(v)
        return vh
    }

    inner class ViewHolder(var mTextView: TextView)
}