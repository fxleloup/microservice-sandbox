#!/usr/bin/env bash
container_name='casepgdb'
if [ "$(docker ps -aq -f name=${container_name})" ];
    then docker start ${container_name}
    else docker run -d --name ${container_name} -p 27123:5432 -e POSTGRES_PASSWORD='123456' -e POSTGRES_USER=caseDb_user -e POSTGRES_DB=Case postgres:9.6.8
fi