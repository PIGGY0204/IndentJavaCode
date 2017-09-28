IndentJavaCode
============

一个简单的自动调整 java 代码缩进的程序

使用方法
-

```  javac IndentJavaCode.java ``` 进行编译

运行

``` java IndentJavaCode [sourceFile] [targetFile]```

**sourceFile:**
要进行缩进的 .java 文件路径

**targetFile:**
缩进后代码保存的位置路径（省略则保存在和 sourceFile 同目录下，名为 indent_sourceName 的文件）

缩进格式
-

本程序仅修改每行代码前的缩进格数，不会修改中间的格式。

行尾遇到 ``` { ``` 下一行的代码将都多一个单位的缩进（默认是四个空格），行首遇到 ``` } ``` 从此行开始缩进就减少一个单位

当行尾未检测到  ``` ; ``` 时下一行将临时增加一个单位缩进（再下一行将恢复）

Bug
-

还未支持 ``` switch ``` 中 ``` case ``` 下的代码缩进