package com.example.administrator.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.myapplication.skill.*

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        SignInSkill().execute()
        val shareSkill = ShareSkill()
        shareSkill.shareNews()
        shareSkill.listenMedia()
        shareSkill.readNews()

        WatchVideoSkill().getVideoList()
        AudioSkill().getAudioColumn()
        ReadNewsSkill().getMessageList()
        LikeSkill().getLikeList()
    }
}
