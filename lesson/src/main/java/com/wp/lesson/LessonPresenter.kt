package com.wp.lesson

import com.google.gson.reflect.TypeToken
import com.wp.base.http.EntityCallback
import com.wp.base.http.HttpClient.get
import com.wp.base.utils.Utils.toast
import com.wp.lesson.LessonPresenter
import com.wp.lesson.entity.Lesson
import java.util.*

class LessonPresenter(private val activity: LessonActivity) {

    companion object {
        const val LESSON_PATH = "lessons"
    }

    private var lessons: List<Lesson> = ArrayList()
    private val type = object : TypeToken<List<Lesson?>?>() {}.type

    fun fetchData() {
        get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message) }
            }

            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread { activity.showResult(lessons) }
            }
        })
    }

    fun showPlayback() {
        val playbackLessons = mutableListOf<Lesson>()
        for (lesson in lessons) {
            if (lesson.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }


}