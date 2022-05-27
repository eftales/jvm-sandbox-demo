rm -rf ~/.sandbox-module/*
mvn clean package -Dmaven.test.skip=true
mv target/sandbox-module-1.0-SNAPSHOT-jar-with-dependencies.jar ~/.sandbox-module/
jps | grep MyApp
