package com.example.aspecttest2

import android.app.Application
import com.example.aspecttest2.fakes.FooBindImpl
import com.example.aspecttest2.fakes.FooBind
import com.example.aspecttest2.fakes.InjectedClassFake
import com.example.aspecttest2.fakes.JavaFake
import com.example.aspecttest2.fakes.KotlinFake
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Component(modules = [FooModule::class, BindsModule::class])
interface ApplicationComponent {
    fun inject(application: MyApplication)
}

@Module
class FooModule {

    @Provides
    fun provideFooJava(): JavaFake {
        return JavaFake()
    }

    @Provides
    fun provideFooKotlin(): KotlinFake {
        return KotlinFake()
    }
}

@Module
interface BindsModule {
    @Binds
    fun bindFoo(foo: FooBindImpl): FooBind
}


class MyApplication : Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerApplicationComponent.create()

    @Inject lateinit var javaFoo: JavaFake
    @Inject lateinit var kotlinFoo: KotlinFake
    @Inject lateinit var injectedClassFake: InjectedClassFake
    @Inject lateinit var fooBind: FooBind

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}