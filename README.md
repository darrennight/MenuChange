MenuChange
==========

图标变形动画

#### 1.加减变形 ####

###### 使用 ######

通过长按切换状态，通过点击来加分或者减分

设置状态监听：

```
mAddDes.setOnStatChangeListener(new OnStateChangeListener(){
    public void changeState(boolean state){
      /*
       * true为加分，false为减分
       */
    }
});
```

###### 动画效果 ######
![add.gif](/Media/add.gif)