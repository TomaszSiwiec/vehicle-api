#!/bin/bash

# Funkcja do uruchomienia docker-compose w danym folderze
run_docker_compose() {
  local folder=$1
  echo "Uruchamianie docker-compose w folderze: $folder"
  (cd $folder && docker-compose up -d)
}

# Uruchomienie docker-compose w podfolderach
run_docker_compose "keycloak"
run_docker_compose "postgres"
run_docker_compose "kafka"

echo "Wszystkie usługi zostały uruchomione."
