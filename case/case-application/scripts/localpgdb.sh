#!/usr/bin/env bash
container_name='case-db'
if [ "$(docker ps -aq -f name=${container_name})" ];
    then docker start ${container_name}
    else docker run -d --name ${container_name} -p 5432:5432 -e POSTGRES_PASSWORD='123456' -e POSTGRES_USER=case_user -e POSTGRES_DB=case-user postgres:9.6.8-alpine
fi