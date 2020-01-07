#!/bin/sh
docker build . -t tennis-aws-lambda-micronaut-app
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 tennis-aws-lambda-micronaut-app"
