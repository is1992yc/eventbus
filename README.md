#Guava EventBus 的简单使用

通过@Subscribe注释Listener, 使Listener能够观察到event并消费event.

使用简单, 直接通过自定义的MyEventBus
```
myEventBus.postAnsy(new EatEvent(user,"菠萝蜜"));
myEventBus.postAnsy(new EatEvent(user,"粑粑柑"));
myEventBus.postAnsy(new EatEvent(user,"哈密瓜"));
myEventBus.postAnsy(new EatEvent(user,"苹果"));
```
可以得到
```
张三 eat 哈密瓜
张三 eat 粑粑柑
张三 eat 苹果
张三 eat 菠萝蜜
```
