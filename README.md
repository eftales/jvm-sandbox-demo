# jvm-sandbox-demo
在 [Online-debugging-demo](https://github.com/70416450/Online-debugging-demo) 的基础上增加了一些代码和脚本

## 启动方法

```bash
# 启动 springboot 项目
cd springboot-demo
mvn clean package
java -jar target/springboot-demo-1.0-SNAPSHOT.jar

# 打开另一个终端，
cd sandbox-module
./update.sh # 编译代码并将 jar 移动到 ~/.sandbox-module 文件夹
./attach.sh # 将代码 attach 到 springboot 进程
./detach.sh # 将代码从 springboot 进程 detach 掉
```
