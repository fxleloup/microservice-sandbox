pipeline {
    agent { docker 'maven:3-alpine' }
    stages {
        stage('compile') {
            steps {
                sh 'mvn clean compileblue'
            }
        }
    }
}