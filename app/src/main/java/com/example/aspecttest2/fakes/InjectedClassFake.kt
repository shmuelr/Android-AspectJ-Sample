package com.example.aspecttest2.fakes

import javax.inject.Inject

class InjectedClassFake @Inject constructor() {
    init {
        println("InjectedClassFake.constructor")
        Thread.sleep(123);
    }
}