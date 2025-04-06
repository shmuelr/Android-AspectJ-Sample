package com.example.aspecttest2.fakes


class KotlinFake {

    // Simulate long initialization
    init {
        println("KotlinFake.constructor")
        Thread.sleep(543)
    }

    fun doFoo() {
        println("KotlinFake.foo")
    }
}