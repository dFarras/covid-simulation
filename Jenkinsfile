pipeline {
    agent { docker { image 'maven:3.6.3-jdk-14' } }
    stages {
        stage('DOWNLOAD') {
            steps {
                git 'https://github.com/dFarras/covid-simulation.git'
            }
        }
        stage('PACKAGE') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
