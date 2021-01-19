#

mvn install -Dmaven.test.skip=true

mvn deploy -Dmaven.test.skip=true

mvn package -Dmaven.test.skip=true -PdevH2I

mvn package -Dmaven.test.skip=true -PcarProduct

mvn package -Dmaven.test.skip=true -Pproduct

mvn deploy:deploy-file -DgroupId=com.baidu.ueditor -DartifactId=ueditor -Dversion=1.1.1-SNAPSHOT -Dpacckaging=jar -Dfile=D:\Program\mavenRepository\com\baidu\ueditor\ueditor\1.1.1\ueditor-1.1.1.jar -DrepositoryId=snapshots -Durl=http://120.24.209.98:8081/nexus/content/repositories/snapshots

mvn install:install-file -Dfile=D:/org.osgi.core-4.3.1.jar -DgroupId=org.osgi -DartifactId=org.osgi.core -Dversion=4.3.1 -Dpackaging=jar
mvn install:install-file -Dfile=D:/aliyun-java-sdk-core-4.5.1.jar -DgroupId=com.aliyun -DartifactId=aliyun-java-sdk-core -Dversion=4.5.3 -Dpackaging=jar


仅清除页面缓存（PageCache）
echo 1 > /proc/sys/vm/drop_caches

清除目录项和inode
echo 2 > /proc/sys/vm/drop_caches

清除页面缓存，目录项和inode
echo 3 > /proc/sys/vm/drop_caches 