::编译jar并打包
call mvn clean package -Dmaven.test.skip=true

:: 将jar编译成docker文件
docker build -t hub.c.163.com/ideacoding/marketing-client:1.0 .

:: 登录docker服务器
docker login -u ideacoding@163.com -p logyz2871.. hub.c.163.com

:: 推送docker文件
docker push hub.c.163.com/ideacoding/marketing-client:1.0