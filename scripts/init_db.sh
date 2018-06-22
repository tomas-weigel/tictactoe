#!/usr/bin/env bash

docker rm -vf postgres

docker run --name postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -p 127.0.0.1:5432:5432 -d postgres

read -p "Postgres startup...." -t 5