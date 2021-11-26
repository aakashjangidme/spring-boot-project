#!/usr/bin/env bash

cd /home/ec2-user/server

sudo $(which java) -jar -Dserver.port=80 \
     todoapp-*.jar > /dev/null 2> /dev/null < /dev/null &