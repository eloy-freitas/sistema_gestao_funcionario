#!/bin/bash 

docker compose -f  docker/postgres_compose.yml up -d 
docker cp ./ddl/database_ddl.sql funcionario_db:/
docker exec funcionario_db psql postgres admin -f /database_ddl.sql
