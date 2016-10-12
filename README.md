# AndroidRxNet
## 项目简介
这个项目总共有4个模块 Net、NetDemo、RxNet、RxNetDemo,可以分成两组 Net、NetDemo 为一组；RxNet、RxNetDemo为另外一组。
其中Net和RxNet为网络监听的库，NetDemo和RxNetDemo分别为前面两个的demo示例。
部分参考：[https://github.com/pwittchen/ReactiveNetwork](https://github.com/pwittchen/ReactiveNetwork)
ReactiveNetwork是一个用RxJava实现的Android网络监听库，可以监听是否有网络连接，网络连接的类型，wifi是否连接



* RxNet需要依赖RxAndroid、RxJava

```
compile 'io.reactivex:rxandroid:1.2.1'
     
compile 'io.reactivex:rxjava:1.1.6'
```

* 注意在使用RxNet的时候需要添加相应的权限
```
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
```

* 相关的github地址

[https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)

[ https://github.com/ReactiveX/RxAndroid]( https://github.com/ReactiveX/RxAndroid)
