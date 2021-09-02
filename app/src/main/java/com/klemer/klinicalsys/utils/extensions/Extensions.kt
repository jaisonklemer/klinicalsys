package com.klemer.klinicalsys.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(
    @IdRes container: Int,
    fragment: Fragment,
    addToBackStack: Boolean
) {
    if (addToBackStack) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    } else {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .commit()
    }

}