#!/usr/bin/env bash

mvn clean package

echo 'Copy files...';

scp -i ~/.ssh/id_rsa \
    target/sweater-1.0-SNAPSHOT.jar \
    jaxx@192.168.88.11:/home/jaxx/web/sweater/public/

echo 'Restart server...';

ssh jaxx@192.168.88.11 << EOF

pgrep java | xargs kill -9
nohup java -jar sweater-1.0-SNAPSHOT.jar > ../logs/spring-log.txt &

EOF

echo 'Bye!'
