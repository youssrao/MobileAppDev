package com.youtelli.studentportal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.add_portal_activity.*
import com.youtelli.studentportal.PortalAdapter.Companion.portals

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_portal_activity)

        initViews()
    }

    private fun initViews() {
        val actionbar = supportActionBar
        actionbar!!.title = "Create a Portal"
        actionbar.setDisplayHomeAsUpEnabled(true)

        btnAddPortal.setOnClickListener() {
            portals.add(Portal(etTitle.text.toString(), etUrl.text.toString()))
            this.finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}