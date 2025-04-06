package com.example.aspecttest2.fakes

import javax.inject.Inject

interface FooBind

class FooBindImpl @Inject constructor(): FooBind {
    init {
        println("BindsTest.constructor")
        Thread.sleep(234)
    }
}