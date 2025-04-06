Sample project for using AspectJ to add logging around Dagger provides & inject functions.

Running the app will produce these logs,
```
[TIMING] Dagger Provide: FooModule.provideFooJava executed in 778 ms (778704100 ns)
[TIMING] Dagger Provide: FooModule.provideFooKotlin executed in 543 ms (543476700 ns)
[TIMING] Dagger Inject: InjectedClassFake.<init> executed in 123 ms (123207400 ns)
[TIMING] Dagger Inject: FooBindImpl.<init> executed in 235 ms (235841700 ns)
```

You can use this to find slow provide/bind calls in your dagger graph and figure out where your code is getting blocked.
