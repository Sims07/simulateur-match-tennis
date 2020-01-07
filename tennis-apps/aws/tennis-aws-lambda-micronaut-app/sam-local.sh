#!/bin/sh
docker build . -t tennis-aws-lambda-micronaut-app
mkdir -p build
docker run --rm --entrypoint cat tennis-aws-lambda-micronaut-app  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

