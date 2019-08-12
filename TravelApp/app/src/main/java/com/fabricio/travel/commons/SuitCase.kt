package com.fabricio.travel.commons

import android.widget.Checkable


data class SuitCase(var name: String, var image: String?) : Checkable {

    var isCheckable: Boolean = false

    override fun isChecked(): Boolean {
        return isCheckable
    }

    override fun toggle() {
        this.isCheckable = !this.isCheckable
    }

    override fun setChecked(p0: Boolean) {
        this.isCheckable = p0
    }
}