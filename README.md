# Android 开发工具集 🚀

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Version](https://img.shields.io/badge/version-1.1.0-green.svg)](https://github.com/your-repo/android-utils)

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
| `CustomDialogUtil` | 自定义对话框管理 | 信息提示、用户确认、输入获取 |
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
| `FilePickerHelper` | 高级文件选择 | 自定义UI、多选支持、文件预览 |
| `FolderPickerHelper` | 文件夹选择 | 目录选择、路径管理 |

### 🔔 通知模块

| 类名 | 功能 | 支持特性 |
|------|------|----------|
| `NotificationHelper` | 通知管理 | 渠道管理、多种通知类型 |

## 📖 使用指南

### 自定义对话框

```java
// 显示简单对话框
Dialog dialog = CustomDialogUtil.showDialog(this, customView);

// 关闭对话框
CustomDialogUtil.closeDialog();
```

### 设备信息获取

```java
// 获取设备基本信息
String model = DeviceInfoUtil.getDeviceModel();
String brand = DeviceInfoUtil.getDeviceBrand();
String manufacturer = DeviceInfoUtil.getDeviceManufacturer();

// 获取系统信息
String androidVersion = DeviceInfoUtil.getAndroidVersion();
int sdkVersion = DeviceInfoUtil.getSdkVersion();

// 获取网络信息
String ipAddress = DeviceInfoUtil.getIpAddress(context);
String networkType = DeviceInfoUtil.getNetworkType(context);
String macAddress = DeviceInfoUtil.getMacAddress(context);

// 获取性能信息
String totalStorage = DeviceInfoUtil.getTotalStorage();
String availableStorage = DeviceInfoUtil.getAvailableStorage();
int cpuCores = DeviceInfoUtil.getCpuCoreCount();
```

### 自定义吐司消息

#### 方法签名与参数说明

1. **简单吐司消息**
   ```java
   public static void showToast(Context context, String message)
   ```


• 参数说明：

• `context`：上下文对象，用于创建 Toast。

• `message`：要显示的文本消息。

• 用途：快速显示一个简单的吐司消息，使用默认样式。


2. 自定义样式吐司消息

```java
   public static void showToast(
       Context context,
       String message,
       float textSize,
       String backgroundColor,
       float radius,
       String textColor,
       int padding
   )
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


示例代码


完整使用示例


```java
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 显示简单吐司
        ToastUtil.showToast(this, "这是一个简单的吐司消息");
        
        // 显示自定义样式吐司
        ToastUtil.showToast(
            this,
            "这是一个自定义样式的吐司消息",
            18f,          // 文字大小为 18sp
            "#3498db",    // 背景颜色为蓝色
            16f,          // 圆角大小为 16dp
            "#ffffff",    // 文字颜色为白色
            16           // 内边距为 16dp
        );
    }
}
```



注意事项

• 颜色值：`backgroundColor`和`textColor`参数需要使用十六进制颜色值，例如`#ffffff`表示白色，`#000000`表示黑色。

• 尺寸单位：`textSize`和`radius`的单位分别为`sp`和`dp`，请根据实际需求调整。

• 内边距：`padding`参数的单位为`dp`，用于控制吐司内容的内边距。

```


### 文件选择器

```java
// 启动文件选择器
FilePickerUtil.pickFile(this);

// 在 onActivityResult 中处理结果
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    String filePath = FilePickerUtil.handleActivityResult(requestCode, resultCode, data, this);
    if (filePath != null && !filePath.startsWith("Failed")) {
        // 文件选择成功，处理文件路径
        Log.d("FilePicker", "Selected file: " + filePath);
    }
}
```

### 多层加密器

```java
try {
    String key = "MySuperSecretKey";
    String plaintext = "Hello, World! 你好，世界！";
    
    // 加密数据
    String encrypted = MultiLayerEncryptor.encrypt(plaintext, key);
    Log.d("Encrypt", "Encrypted: " + encrypted);
    
    // 解密数据
    String decrypted = MultiLayerEncryptor.decrypt(encrypted, key);
    Log.d("Decrypt", "Decrypted: " + decrypted);
    
} catch (Exception e) {
    e.printStackTrace();
}
```

### 权限设置工具

```java
// 跳转到应用设置页面
PermissionSettingsUtil.openAppSettings(context, getPackageName());

// 跳转到通知设置页面
PermissionSettingsUtil.openAppNotificationSettings(context, getPackageName());

// 跳转到电池优化设置页面
PermissionSettingsUtil.openAppBatterySettings(context, getPackageName());

// 跳转到悬浮窗权限设置页面
PermissionSettingsUtil.openAppOverlaySettings(context, getPackageName());

// 跳转到所有文件访问权限设置页面
PermissionSettingsUtil.openAppAllFilesAccessSettings(context, getPackageName());
```

### 状态栏工具

```java
// 设置状态栏颜色
StatusBarUtil.setStatusBarColor(this, "#FF6200EE");

// 将颜色字符串转换为 int 值
int color = StatusBarUtil.colorStringToInt("#FF6200EE");
```

### 动画效果

```java
// 弹跳动画 - 默认振幅
BounceAnimation.start(view, 1000);

// 弹跳动画 - 自定义振幅
BounceAnimation.start(view, 1000, 50f);

// 正弦波运动动画 - 默认参数
SineMoveAnimation.start(view);

// 正弦波运动动画 - 自定义参数
SineMoveAnimation.start(view, 2000, 80f, 3);

// 缩放动画 - 默认参数
ScaleAnimation.start(view, 1000);

// 缩放动画 - 自定义参数
ScaleAnimation.start(view, 1000, 2.0f, 0.8f);
```

### 高级文件选择器

```java
// 启动高级文件选择器
FilePickerHelper.pickFile(this, "*/*");

// 处理选择结果
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    String filePath = FilePickerHelper.handleActivityResult(requestCode, resultCode, data, this);
    if (filePath != null) {
        // 处理选择的文件
        String extension = FilePickerHelper.getFileExtension(filePath);
        String mimeType = FilePickerHelper.getMimeType(filePath);
    }
}
```

### 文件夹选择器

```java
// 启动文件夹选择器
FolderPickerHelper.pickFolder(this);

// 处理选择结果
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    String folderPath = FolderPickerHelper.handleActivityResult(requestCode, resultCode, data, this);
    if (folderPath != null && !folderPath.equals("no folder")) {
        // 处理选择的文件夹
        Log.d("FolderPicker", "Selected folder: " + folderPath);
    }
}
```

### 通知管理

```java
// 显示简单通知
NotificationHelper.showNotification(
    context,
    "通知标题",
    "通知内容",
    1001  // 通知ID
);
```

## 🔧 API 文档

### CustomDialogUtil

```java
public class CustomDialogUtil {
    // 显示自定义对话框
    public static Dialog showDialog(Context context, View view)
    
    // 关闭对话框
    public static void closeDialog()
}
```

### DeviceInfoUtil

```java
public class DeviceInfoUtil {
    // 设备信息
    public static String getDeviceModel()
    public static String getDeviceBrand()
    public static String getDeviceManufacturer()
    public static String getDeviceCpuInfo()
    
    // 系统信息
    public static String getAndroidVersion()
    public static int getSdkVersion()
    
    // 网络信息
    public static String getIpAddress(Context context)
    public static String getNetworkType(Context context)
    public static String getMacAddress(Context context)
    
    // 性能信息
    public static String getTotalStorage()
    public static String getAvailableStorage()
    public static int getCpuCoreCount()
}
```

### MultiLayerEncryptor

```java
public class MultiLayerEncryptor {
    // 加密数据
    public static String encrypt(String plaintext, String key) throws Exception
    
    // 解密数据
    public static String decrypt(String encryptedStr, String key) throws Exception
}
```

### 其他 API

更多详细的 API 文档请参考各个类的源码注释。

## 🎨 示例代码

### 完整使用示例

```java
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 设置状态栏
        StatusBarUtil.setStatusBarColor(this, "#FF6200EE");
        
        // 获取设备信息
        String deviceInfo = "设备: " + DeviceInfoUtil.getDeviceModel() + 
                           "\n系统: Android " + DeviceInfoUtil.getAndroidVersion();
        
        // 显示设备信息
        TextView infoView = new TextView(this);
        infoView.setText(deviceInfo);
        infoView.setPadding(50, 50, 50, 50);
        
        CustomDialogUtil.showDialog(this, infoView);
        
        // 添加弹跳动画
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            BounceAnimation.start(v, 500);
            
            // 选择文件
            FilePickerUtil.pickFile(this);
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        // 处理文件选择结果
        String filePath = FilePickerUtil.handleActivityResult(requestCode, resultCode, data, this);
        if (filePath != null && !filePath.startsWith("Failed")) {
            // 显示通知
            NotificationHelper.showNotification(
                this,
                "文件选择成功",
                "已选择文件: " + new File(filePath).getName(),
                1001
            );
        }
    }
}
```

## 🤝 贡献指南

我们欢迎社区贡献！请遵循以下步骤：

1. Fork 此仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

### 代码规范

- 遵循 Java 编码规范
- 添加适当的注释和文档
- 确保代码向后兼容
- 添加必要的错误处理

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
```

## 📞 联系方式

- 项目主页: [GitHub Repository](https://github.com/your-repo/android-utils)
- 问题反馈: [Issues](https://github.com/your-repo/android-utils/issues)
- 邮箱: your-email@example.com

## 🙏 致谢

感谢所有为此项目做出贡献的开发者！

---

**⭐ 如果这个项目对你有帮助，请给我们一个 Star！⭐**
