package com.purakastudio.android.pingpongscoreboard

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // set variable
    var setBlue = 0
    var setRed = 0
    var pointRed = 0
    var pointBlue = 0

    // variable temp name
    var tmpplayer1 = "Player 1"
    var tmpplayer2 = "Player 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val player1 = intent.getStringExtra("player1")
        val player2 = intent.getStringExtra("player2")
        val service = intent.getStringExtra("service")

        // inisialisasi untuk memunculkan bola, player 1 adalah tim merah
        if (service == "Player 1") {
            // bola ada di tim merah
            blueBall.visibility = View.GONE
        } else if (service == "Player 2") {
            // bola ada di tim biru
            redBall.visibility = View.GONE
        }

        redPlayer.text = player1
        bluePlayer.text = player2

        // penambahan point untuk tim red
        pointRedBtn.setOnClickListener {
            pointRed = pointRed + 1
            if (pointRed < 10) {
                redTeamScore.text = "0"+pointRed.toString()
            } else {
                redTeamScore.text = pointRed.toString()
            }
            // aturan pindah bola
            if ((pointRed < 11) && (pointBlue < 11)) {
                if ((pointRed + pointBlue) % 2 == 0) {
                    pindahBola()
                }
            } else {
                pindahBola()
                // aturan menang set
                if (((pointRed == 11) && (pointBlue < 10)) || (pointRed - pointBlue == 2)) {
                    setRed = setRed + 1
                    pointRed = 0
                    pointBlue = 0
                    redSet.text = setRed.toString()
                    redTeamScore.text = "0"+pointRed.toString()
                    blueTeamScore.text = "0"+pointBlue.toString()

                    // ganti warna
                    gantiWarna()

                }
            }
        }

        // pengurangan point untuk tim red
        pointBackRedBtn.setOnClickListener {
            if (pointRed > 0) {
                pointRed = pointRed - 1
                if (pointRed < 10) {
                    redTeamScore.text = "0"+pointRed.toString()
                } else {
                    redTeamScore.text = pointRed.toString()
                }
                // aturan pindah bola
                if ((pointRed < 11) && (pointBlue < 11) ) {
                    if (pointRed == 0) {
                        resetSet()
                    } else {
                        if ((pointRed + pointBlue) % 2 == 0) {
                            pindahBola()
                        }
                    }
                } else {
                    pindahBola()
                    // aturan menang set
                    if (((pointRed == 11) && (pointBlue < 10)) || (pointRed - pointBlue == 2)) {
                        setRed = setRed + 1
                        pointRed = 0
                        pointBlue = 0
                        redSet.text = setRed.toString()
                        redTeamScore.text = "0"+pointRed.toString()
                        blueTeamScore.text = "0"+pointBlue.toString()
                    }
                }
            }
        }

        // penambahan point untuk tim blue
        pointBlueBtn.setOnClickListener {
            pointBlue = pointBlue + 1
            if (pointBlue < 10) {
                blueTeamScore.text = "0"+pointBlue.toString()
            } else {
                blueTeamScore.text = pointBlue.toString()
            }
            // aturan pindah bola
            if ((pointRed < 11) && (pointBlue < 11)) {
                if ((pointRed + pointBlue) % 2 == 0) {
                    pindahBola()
                }
            } else {
                pindahBola()
                // aturan menang set
                if (((pointBlue == 11) && (pointRed < 10)) || (pointBlue - pointRed == 2)) {
                    setBlue = setBlue + 1
                    pointRed = 0
                    pointBlue = 0
                    blueSet.text = setBlue.toString()
                    redTeamScore.text = "0"+pointRed.toString()
                    blueTeamScore.text = "0"+pointBlue.toString()

                    // ganti warna
                    gantiWarna()
                }
            }
        }

        // pengurangan point untuk tim blue
        pointBackBlueBtn.setOnClickListener {
            if (pointBlue > 0) {
                pointBlue = pointBlue - 1
                if (pointBlue < 10) {
                    blueTeamScore.text = "0"+pointBlue.toString()
                } else {
                    blueTeamScore.text = pointBlue.toString()
                }
                // aturan pindah bola
                if ((pointRed < 11) && (pointBlue < 11) ) {
                    if (pointBlue == 0) {
                        resetSet()
                    } else {
                        if ((pointRed + pointBlue) % 2 == 0) {
                            pindahBola()
                        }
                    }
                } else {
                    pindahBola()
                    // aturan menang set
                    if (((pointBlue == 11) && (pointRed < 10)) || (pointBlue - pointRed == 2)) {
                        setBlue = setBlue + 1
                        pointRed = 0
                        pointBlue = 0
                        blueSet.text = setBlue.toString()
                        redTeamScore.text = "0"+pointRed.toString()
                        blueTeamScore.text = "0"+pointBlue.toString()
                    }
                }
            }
        }

    }

    fun pindahBola() {
        if (redBall.visibility == View.VISIBLE) {
            blueBall.visibility = View.VISIBLE
            redBall.visibility = View.GONE
        } else {
            blueBall.visibility = View.GONE
            redBall.visibility = View.VISIBLE
        }
    }

    fun gantiWarna() {
        val layoutColor = redTeamScoreLayout.background as ColorDrawable
        if (layoutColor.color == resources.getColor(R.color.colorRedTeam, theme)) {
            redTeamScoreLayout.setBackgroundColor(resources.getColor(R.color.colorBlueTeam, theme))
            blueTeamScoreLayout.setBackgroundColor(resources.getColor(R.color.colorRedTeam, theme))
            pointRedBtn.backgroundTintList = resources.getColorStateList(R.color.colorBlueTeam, theme)
            pointBackRedBtn.backgroundTintList = resources.getColorStateList(R.color.colorBlueTeam, theme)
            pointBlueBtn.backgroundTintList = resources.getColorStateList(R.color.colorRedTeam, theme)
            pointBackBlueBtn.backgroundTintList = resources.getColorStateList(R.color.colorRedTeam, theme)
            setBlue = setBlue + setRed
            setRed = setBlue - setRed
            setBlue = setBlue - setRed
            blueSet.text = setBlue.toString()
            redSet.text = setRed.toString()
            tmpplayer1 = redPlayer.text.toString()
            tmpplayer2 = bluePlayer.text.toString()
            redPlayer.text = tmpplayer2
            bluePlayer.text = tmpplayer1
        } else {
            redTeamScoreLayout.setBackgroundColor(resources.getColor(R.color.colorRedTeam, theme))
            blueTeamScoreLayout.setBackgroundColor(resources.getColor(R.color.colorBlueTeam, theme))
            pointRedBtn.backgroundTintList = resources.getColorStateList(R.color.colorRedTeam, theme)
            pointBackRedBtn.backgroundTintList = resources.getColorStateList(R.color.colorRedTeam, theme)
            pointBlueBtn.backgroundTintList = resources.getColorStateList(R.color.colorBlueTeam, theme)
            pointBackBlueBtn.backgroundTintList = resources.getColorStateList(R.color.colorBlueTeam, theme)
            setBlue = setBlue + setRed
            setRed = setBlue - setRed
            setBlue = setBlue - setRed
            blueSet.text = setBlue.toString()
            redSet.text = setRed.toString()
            tmpplayer1 = redPlayer.text.toString()
            tmpplayer2 = bluePlayer.text.toString()
            redPlayer.text = tmpplayer2
            bluePlayer.text = tmpplayer1
        }
    }

    fun resetSet() {
        setBlue = 0
        setRed = 0
        pointBlue = 0
        pointRed = 0
        blueSet.text = "0"
        redSet.text = "0"
        redBall.visibility = View.VISIBLE
        blueBall.visibility = View.GONE
    }
}