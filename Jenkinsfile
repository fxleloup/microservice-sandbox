pipeline {
    agent { docker 'maven:3-alpine' }
    stages {
        stage('compile') {
            sh 'mvn clean compileblue'
        }
    }
}