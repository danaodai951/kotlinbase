package com.wp.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.wp.base.utils.CacheUtils.get
import com.wp.base.utils.CacheUtils.save
import com.wp.base.utils.Utils.toast
import com.wp.kotlindemo.entity.User
import com.wp.kotlindemo.widget.CodeView
import com.wp.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey = "username"
    private val passwordKey = "password"

    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(get(usernameKey))
        et_password.setText(get(passwordKey))

        val btn_login = findViewById<Button>(R.id.btn_login)
        val img_code = findViewById<CodeView>(R.id.code_view)

        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username!!.text.toString()
        val password = et_password!!.text.toString()
        val code = et_code!!.text.toString()
        val user =
            User(username, password, code)
        if (verify(user)) {
            save(usernameKey, username)
            save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        if (user.username != null && user.username!!.length < 4) {
            toast("用户名不合法")
            return false
        }
        if (user.password != null && user.password!!.length < 4) {
            toast("密码不合法")
            return false
        }
        return true
    }
}