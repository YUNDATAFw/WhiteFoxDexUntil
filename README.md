# Android 开发工具集 🚀

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Version](https://img.shields.io/badge/version-1.2.0-green.svg)](https://github.com/your-repo/android-utils)

一个功能全面、高度实用的 Android 开发辅助工具集，提供了从 UI 定制、系统信息获取、文件管理到数据安全等多个模块的解决方案。

## 📋 目录

- [功能特性](#-功能特性)
- [快速开始](#-快速开始)
- [模块介绍](#-模块介绍)
- [使用指南](#-使用指南)
- [API 文档](#-api-文档)
- [示例代码](#-示例代码)
- [贡献指南](#-贡献指南)
- [许可证](#-许可证)

## ✨ 功能特性

### 🎨 UI & 交互
- **自定义对话框** - 简化对话框创建和管理
- **状态栏工具** - 轻松定制状态栏样式和颜色
- **动画效果** - 弹跳、正弦波、缩放等丰富动画

### 📁 文件管理
- **文件选择器** - 简化文件选择流程
- **高级文件助手** - 支持复杂文件操作和路径解析
- **文件夹选择器** - 专业的目录选择功能

### 🔒 安全 & 权限
- **多层加密器** - 提供高级数据加密保护
- **权限管理** - 简化 Android 权限请求和设置

### 📱 系统工具
- **设备信息获取** - 全面的设备硬件和软件信息
- **通知管理** - 简化通知创建和渠道管理

## 🚀 快速开始

### 系统要求

- **最低 Android API**: 21 (Android 5.0)
- **编译 SDK 版本**: 34+
- **Java 版本**: 8+

### 集成方式

#### 方式一：直接导入源码
1. 下载项目源码
2. 将所需的工具类复制到你的项目中
3. 确保添加必要的依赖和权限

#### 方式二：模块化使用
根据需要选择特定模块：

```java
// 只需要对话框功能
import your.package.CustomDialogUtil;

// 只需要加密功能  
import your.package.MultiLayerEncryptor;
```

### 必要权限

在 `AndroidManifest.xml` 中添加所需权限：

```xml
<!-- 设备信息获取 -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

<!-- 文件操作 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<!-- 通知 -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

<!-- 权限设置跳转 -->
<uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />
```

## 📦 模块介绍

### 🎯 核心工具类

| 模块 | 功能描述 | 主要用途 |
|------|----------|----------|
| `CustomDialogUtil` 自定义对话框管理 | 信息提示、用户确认、输入获取 |
| `DeviceInfoUtil` | 设备信息获取 | 设备适配、数据统计、问题诊断 |
| `FilePickerUtil` | 文件选择器 | 文件上传、导入导出 |
| `MultiLayerEncryptor` | 多层数据加密 | 敏感数据保护、安全存储 |
| `PermissionSettingsUtil` | 权限管理 | 权限请求、设置页跳转 |
| `StatusBarUtil` | 状态栏定制 | UI 美化、沉浸式体验 |

### 🎬 动画模块

| 动画类 | 效果 | 应用场景 |
|--------|------|----------|
| `BounceAnimation` | 弹跳效果 | 按钮反馈、提示动画 |
| `SineMoveAnimation` | 正弦波运动 | 加载指示器、漂浮效果 |
| `ScaleAnimation` | 缩放动画 | 图片预览、卡片展开 |

### 📂 文件管理模块

| 助手类 | 功能 | 特点 |
|--------|------|------|
| `FilePickerHelper` | 文件选择 | 多类型文件选择 |
| `FolderPickerHelper` | 文件夹选择 | 目录选择、路径管理 |

### 🔔 吐司模块（小提示弹窗）

| 类名 | 功能 | 支持特性 |
|------|------|----------|
| `Views.Toast` | 通知管理 | 渠道管理、多种通知类型 |

## 📖 使用指南

### 自定义对话框

```iapp
// 显示简单对话框
    nvw(-1,null,"线性布局","width=-1\nheight=-1\norientation=vertical\nbackgroundcolor=0",ss.弹窗布局)
    addv(ss.弹窗布局,"弹窗.iyu")
    //ID 6
    gvs(ss.弹窗布局,弹窗.6,title)
    //获取标题
    us(title,"text","hello🤗")
    //设置标题
    
    //ID 6
    gvs(ss.弹窗布局,弹窗.10,text)
    //获取标题
    us(text,"text","你好😄")
    //设置标题
    
    loadjar("classes.dex", dex)

    cls(dex, "CustomDialogUtil", class)

    //调用静态方法 CustomDialogUtil showDialog 方法

    javax(ss.对话框, null, class, "showDialog", "Context", activity ,"android.view.View",ss.弹窗布局)


// 关闭对话框
    java(null,ss.对话框,"android.app.Dialog.dismiss")
```

### 设备信息获取

```iapp
loadjar("classes.dex", dex)

cls(dex, "DeviceInfoUtil", class)
// 获取设备信息
    

    //获取设备型号

    javax(ret, null, class, "getDeviceModel")

    tw(ret)
    //设备品牌
    javax(ret, null, class, "getDeviceBrand")

    tw(ret)
    //设备制造商
    javax(ret, null, class, "getDeviceManufacturerand")

    tw(ret)
    
// 获取系统信息
    //处理器
    javax(ret, null, class, "getDeviceCpuInfo")

    tw(ret)
    
    //Ip
    javax(ret, null, class, "getIpAddress","Context",activity)

    tw(ret)
    
    //其他请参考demo项目

```

### 自定义吐司消息

#### 方法签名与参数说明

1. **简单吐司消息**
   ```iapp
    loadjar("classes.dex", dex)

    cls(dex, "Views.Toast", class)

    
    //调用静态方法 Toast 的 showToast 方法

    javax(null, null, class, "showToast", "Context", activity ,"String", "helloMessage")

   
   ```


• 参数说明：

• `context`：上下文对象，用于创建 Toast。

• `message`：要显示的文本消息。

• 用途：快速显示一个简单的吐司消息，使用默认样式。


2. 自定义样式吐司消息

```iapp
    loadjar("classes.dex", dex)

    cls(dex, "Views.Toast", class)

    
    //调用静态方法 Toast 的 showToast 方法

    javax(null, null, class, "showToast", "Context", activity ,"String", "helloMessage", "float", "18f", "String", "#fefefe", "float", "10f" , "String","#343536", "int", 10)

   
   ```


• 参数说明：

• `context`：上下文对象，用于创建 Toast。

• `message`：要显示的文本消息。

• `textSize`：文本的字体大小，单位为`sp`。

• `backgroundColor`：吐司背景颜色，使用十六进制颜色值（例如`#ffffff`）。

• `radius`：吐司背景的圆角大小，单位为`dp`。

• `textColor`：文本颜色，使用十六进制颜色值（例如`#000000`）。

• `padding`：吐司内容的内边距，单位为`dp`。

• 用途：显示一个自定义样式的吐司消息，允许用户自定义字体大小、背景颜色、圆角大小、文本颜色和内边距。




注意事项

• 颜色值：`backgroundColor`和`textColor`参数需要使用十六进制颜色值，例如`#ffffff`表示白色，`#000000`表示黑色。

• 尺寸单位：`textSize`和`radius`的单位分别为`sp`和`dp`，请根据实际需求调整。

• 内边距：`padding`参数的单位为`dp`，用于控制吐司内容的内边距。



### 多层加密器

```iapp
    //为测试接口不予示例

```


### 权限设置工具

```iapp
    loadjar("classes.dex", dex)

    cls(dex, "PermissionSettingsUtil", class)
    
    sss packageName="com.iapp.app"
    //这里要修改为实际的包名
    
    // 跳转到应用设置页面

    //调用静态方法 PermissionSettingsUtil类 openAppSettings 方法

    javax(null, null, class, "openAppSettings", "Context", activity ,"String",sss.packageName)

// 跳转到通知设置页面
    javax(null, null, class, "openAppNotificationSettings", "Context", activity ,"String",sss.packageName)

// 其他参考demo项目

```

### 状态栏工具

```iapp
    loadjar("classes.dex", dex)

    cls(dex, "StatusBarUtil", class)
    
// 设置状态栏颜色
    javax(null, null, class, "setStatusBarColor", "Activity", activity ,"String","#ffd7afff")



```

### 动画效果

```iapp
    // 弹跳动画 
    loadjar("classes.dex", dex)

    cls(dex, "Animation.BounceAnimation", class)

    //调用静态方法 Animation.BounceAnimation BounceAnimation 方法
  View 控件对象 long 显示时间 float 移动幅度
    javax(null, null, class, "start", "android.view.View", st_vW ,"long",500, "float",10)
    //其他参考demo项目

```

### 文件选择器

```iapp
    // 启动文件选择器
    loadjar("classes.dex", dex)
    cls(dex, "File.FilePickerHelper", class)
    //调用静态方法 File.FilePickerHelper pickFile 方法
  
    javax(null, null, class, "pickFile", "Activity", activity,"String","*/*")
    
    //回调事件
    f(st_sC==1)
    {
      loadjar("classes.dex", dex)
      cls(dex, "File.FilePickerHelper", class)
      javax(c, null, class, "handleActivityResult", "int", st_sC, "int",st_lC, "android.content.Intent",st_iT, "Activity", activity)
      syso(c)
    }


```

### 文件夹选择器

```iapp
    // 启动文件夹选择器
    loadjar("classes.dex", dex)
    cls(dex, "File.FolderPickerHelper", class)
    //调用静态方法 File.FilePickerHelper pickFile 方法
  
    javax(null, null, class, "pickFolder", "Activity", activity)

    
    //回调事件
    f(st_sC==2)
    {
      loadjar("classes.dex", dex)
      cls(dex, "File.FolderPickerHelper", class)
      javax(c, null, class, "handleActivityResult", "int", st_sC, "int",st_lC, "android.content.Intent",st_iT, "Activity", activity)
      syso(c)
    }
    
```

### 通知管理

```iapp
// 正在开发中

```


## 🎨 示例代码



## 📄 许可证

本项目采用 [Apache License 2.0](https://opensource.org/licenses/Apache-2.0) 许可证。

```
Copyright 2024 Android Utils Library

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

版权所有 2024 Android Utils Library

根据 Apache 许可证 2.0 版（“许可证”）获得许可;
除非遵守许可，否则您不得使用此文件。
您可以在以下网址获取许可证副本

http://www.apache.org/licenses/LICENSE-2.0

除非适用法律要求或书面同意，否则软件
根据许可分发的依据是按“原样”分发的，
不附带任何明示或暗示的保证或条件。
请参阅许可证，了解管理权限的特定语言，以及
许可证的限制。
```

## 📞 联系方式

- 项目主页: [WhiteFoxDexUntil/YUNG-DEX](https://github.com/YUNDATAFw/WhiteFoxDexUntil)
- 问题反馈: [YUNDATAFw](https://github.com/YUNDATAFw)
- 邮箱:  air114514homo@outlook.com

## 🙏 致谢

感谢所有为此项目做出贡献的开发者！

---

**⭐ 如果这个项目对你有帮助，请给我们一个 Star！⭐**
