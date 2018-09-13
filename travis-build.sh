#!/usr/bin/env bash
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar
if [[ $TRAVIS_PULL_REQUEST == "false" ]] && [[ $TRAVIS_BRANCH == "master" ]]; then
    cd business-number
    mvn dockerfile:build@build dockerfile:tag@latest dockerfile:tag@version dockerfile:push@latest dockerfile:push@version

    cd ../case/case-application/
    mvn dockerfile:build@build dockerfile:tag@latest dockerfile:tag@version dockerfile:push@latest dockerfile:push@version
fi
