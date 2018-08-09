# PeasLoadingView

A peas ratationloading view(一个小豆豆旋转loading view).


[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/PeasLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/PeasLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)

<br>

  * [中文](#%E4%B8%AD%E6%96%87)
  * [English](#english)
  * [License](#license)

<br>

![gif](https://github.com/samlss/PeasLoadingView/blob/master/screenshots/screenshot3.gif)



## 中文

### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:PeasLoadingView:1.1'
}
```

布局中使用：
```
<com.iigo.library.PeasLoadingView
            android:id="@+id/plv_loading1"
            app:peas_count="8"
            app:peas_radius="8.5"
            android:layout_width="50dp"
            android:layout_height="50dp" />
```

<br>

代码中使用：
```
  peasLoadingView.start(); //开始动画
  peasLoadingView.stop(); //结束动画
  
  peasLoadingView.setPeasCount(7);//设置豆豆数量
  peasLoadingView.setInterpolator(new LinearInterpolator()); //设置动画插值器
  peasLoadingView.setPeasColors(new int[]{Color.RED, Color.WHITE, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.GRAY}); //设置颜色数组
```

<br>

属性说明：

| 属性        | 说明           |
| ------------- |:-------------:|
| peas_radius      | 豆豆大小，半径 |
| peas_count | 豆豆数量 |

<br>

如果不能满足你的需要，你可以下载源码自行修改。

## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:PeasLoadingView:1.1'
}
```


in layout.xml：
```
<com.iigo.library.PeasLoadingView
            android:id="@+id/plv_loading1"
            app:peas_count="8"
            app:peas_radius="8.5"
            android:layout_width="50dp"
            android:layout_height="50dp" />
```

<br>

in java code：
```
  peasLoadingView.start(); //start animation
  peasLoadingView.stop(); //stop animation
  
  peasLoadingView.setPeasCount(7);//set the peas count
  peasLoadingView.setInterpolator(new LinearInterpolator()); //set the animation interpolator
  peasLoadingView.setPeasColors(new int[]{Color.RED, Color.WHITE, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.GRAY}); //set the color array
```

<br>

Attributes description：

| attr        | description  |
| ------------- |:-------------:|
| peas_radius      | the peas size, radius |
| peas_count | the peas count |

If you can not meet your needs, you can download the source code to modify it.

[id]: http://example.com/ "Optional Title Here"

## [LICENSE](https://github.com/samlss/PeasLoadingView/blob/master/LICENSE)
