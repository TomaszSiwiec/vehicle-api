#!/bin/bash

run_docker_compose() {
  local folder=$1
  echo "Running docker-compose in folder: $folder"
  (cd $folder && docker-compose up -d)
}

run_docker_compose "keycloak"
run_docker_compose "postgres"
run_docker_compose "kafka"

echo "All services started!"
