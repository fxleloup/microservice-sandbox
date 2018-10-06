#!/usr/bin/env bash
#Frontends build
cd person/person-frontend
npm run build:ci
cd ../..

#General Maven build
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar

#Docker build
if [[ $TRAVIS_PULL_REQUEST == "false" ]] && [[ $TRAVIS_BRANCH == "master" ]]; then
    cd business-number
    mvn dockerfile:build@build dockerfile:tag@latest dockerfile:tag@version dockerfile:push@latest dockerfile:push@version

    cd ../case/case-application/
    mvn dockerfile:build@build dockerfile:tag@latest dockerfile:tag@version dockerfile:push@latest dockerfile:push@version
fi
