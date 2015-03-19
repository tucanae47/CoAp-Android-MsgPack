Alpha MsgPack CoAp Android Client
===

Android Studio CoAp client based on last version of [Eclipse Californium](https://github.com/eclipse/californium) 


imported pgks from [californium core](https://github.com/eclipse/californium/tree/master/californium-core
) and [element-conector](https://github.com/eclipse/californium.element-connector/tree/master/src/main/java/org/eclipse/californium/elements
)

It connects with an Asynctask to ponte server at coap://localhost:5683

##  MsgPack support

using v6 branch from
https://github.com/msgpack/msgpack-java 
more exactly 
  compile 'org.msgpack:msgpack:0.6.11'
  
##  Usage:
-Git clone this ponte fork with msg-pack https://github.com/tucanae47/ponte

-start server 

-subsbrice to mosquitto:
```
    mosquitto_sub -h yourserverip -t "hello" -v
```

at mosquito output you will something like
```
hello 12346789,pi on the network,71,20,pi,3.1426000595092773,64,3234
```






