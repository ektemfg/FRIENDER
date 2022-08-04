package com.jorfald.friender

import com.jorfald.friender.viewModel.FriendsViewModel
import junit.framework.TestCase
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class viewModelTests : TestCase() {
    private lateinit var viewModel: FriendsViewModel
    public override fun setUp() {
        super.setUp()
    }
    // Jeg har testet DB/DAO, som viewmodel egentlig kaller på via repository,
    // Derfor er det ikke nødvendig med testing av de funksjonene her.

}