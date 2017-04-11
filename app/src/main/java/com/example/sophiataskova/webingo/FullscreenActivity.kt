package com.example.sophiataskova.webingo

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.github.jinatonic.confetti.CommonConfetti
import com.mapzen.speakerbox.Speakerbox
import org.jetbrains.anko.find
import java.util.concurrent.ScheduledFuture




/**
 * Plays a Bingo game; the numbers that show up are dependent on the system time
 */
class FullscreenActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mCurrentNumberTV: TextView? = null
    private var mContentView: ViewGroup? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mBingoButton: Button? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    var mTellerHandle: ScheduledFuture<*>? = null
    val speakerbox: Speakerbox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)

        Speakerbox(applicationContext as Application?)

        mContentView = find(R.id.content)
        mRecyclerView = find(R.id.grid)
        mCurrentNumberTV = find(R.id.current_bingo_number)
        mBingoButton = find(R.id.bingo)

        (mRecyclerView as RecyclerView).setHasFixedSize(true)
        mLayoutManager = GridLayoutManager(this, 5) as RecyclerView.LayoutManager?
        (mRecyclerView as RecyclerView).layoutManager = mLayoutManager

        bingoCard = generateRandomBingoCard()

        mAdapter = BingoButtonAdapter(bingoCard.toTypedArray())
        (mRecyclerView as RecyclerView).adapter = mAdapter

        newGame()
    }

    private fun winBingo() {
        CommonConfetti.rainingConfetti(mContentView, intArrayOf(Color.BLACK))
                .stream(2000)
        newGame()
    }

    private fun newGame() {
        (mCurrentNumberTV as TextView).text = "READY?"
        mBingoButton?.setTextColor(ContextCompat.getColor(this, R.color.redApple))
        mBingoButton?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        mBingoButton?.text = "NEW GAME"

        mBingoButton?.setOnClickListener {
            resetBoard()
            scheduleShowBingoNumbers()
        }
    }

    private fun resetBoard() {
        mBingoButton?.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        mBingoButton?.setBackgroundColor(ContextCompat.getColor(this, R.color.redApple))
        mBingoButton?.text = "BINGO!"
        (mBingoButton as Button).setOnClickListener {
            if (checkForBingo()) {
                winBingo()
            }
        }

        bingoCard = generateRandomBingoCard()
        mAdapter = BingoButtonAdapter(bingoCard.toTypedArray())
        (mRecyclerView as RecyclerView).adapter = mAdapter

        mAdapter?.notifyDataSetChanged()
    }

    fun scheduleShowBingoNumbers() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                currentNumber = getBingoNumToShow()
                currentBingoNumber = numberToBingoNumber(currentNumber)
                (mCurrentNumberTV as TextView).text = currentBingoNumber

                speakerbox?.play(currentBingoNumber)
                handler.postDelayed(this, 5000)
            }
        }, 0)
    }

    override fun onPause() {
        super.onPause()
        mTellerHandle?.cancel(true)
    }

    companion object {
        var currentBingoNumber: String = "READY?"
        var currentNumber: Int = 0
        var bingoCard : Set<Int> = LinkedHashSet<Int>()
        var selectedBingoNumbers: Set<Int> = LinkedHashSet<Int>()
    }


}
