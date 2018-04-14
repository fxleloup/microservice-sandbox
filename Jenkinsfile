pipeline {
    stages {
        stage('compile') {
            withMaven {
                sh 'mvn clean compileblue'
            }
        }
    }
}