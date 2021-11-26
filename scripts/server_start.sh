#!/usr/bin/env bash

cd /home/ec2-user/server

sudo $(which java) -jar -Dserver.port=80 \
      spring-boot-todo.jar > /dev/null 2> /dev/null < /dev/null &