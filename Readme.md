### 智慧物联农场

#### 概述

该项目分前台和后台，前台用于租户注册登录，并上线自己的产品设备，用于在线访问控制。后台是管理员用于处理租户申请，产品，设备，地域管理等。

农场硬件设备和服务器采用mqtt通信协议进行通信，双方订阅topic，使得双方可以互相通信。

#### 后台项目模块分析

采用微服务划分多个子模块，每个模块作为一个独立的服务，模块之间采用openfeign实现互通调用。

* 基础模块
  * qf-common-base：主要存储每个项目都依赖的通用实体类，注解等等
  * qf-common-profile: 存放所有的配置文件
  * qf-common-sdk：主要是消息队列的通用类，配置和主题
* qf-auth: 认证中心，主要是对第三方设备进行认证
* qf-center-data：数据存储中心，主要通过nosql（redis，mongodb）存储各种设备等相关的数据
* qf-center-service：主要是提供访问数据库mysql的api服务
* qf-api: 对外提供的可以访问的发送指令的api项目
* qf-gateway：对外提供了唯一接口的网关
  * qf-driver-gateway: 主要是为外部的硬件设备的访问提供的网关
  * qf-manger-gateway：主要是访问后台系统的网关
    * 管理员页面
    * 租户页面
* qf-tentant-manger：租户系统的后台，主要为租户的前端项目提供服务
* qf-user-manger：系统管理的后台，主要为管理员的前端项目提供服务
* qf-mqtt-driver：主要提供了mqtt驱动服务的服务

#### 前后台页面展示

* 行政区区域管理

  ![行政区管理](https://github.com/chenying-wwl/newito/blob/main/images/%E8%A1%8C%E6%94%BF%E5%8C%BA%E7%AE%A1%E7%90%86.png)

* 租户审核

![租户审核管理](\images\租户审核管理.png)

* 租户管理

![租户管理](images\租户管理.png)

* 行业管理

![行业管理](images\行业管理.png)

* 设备类型管理

![设备类型管理](images\设备类型管理.png)

* 产品管理

![产品管理](images\产品管理.png)

* 租户登录

![租户登录](images\租户登录.png)

* 租户主页

![租户主页](images\租户主页.png)

* 租户产品

![租户产品管理](images\租户产品管理.png)

* 租户设备

![租户设备](images\租户设备.png)

