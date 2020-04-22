pipeline {
    agent any
    stages {
        stage('DOWNLOAD') {
            steps {
                git 'https://github.com/dFarras/covid-simulation.git'
            }
        }
        stage('PACKAGE') {
            agent { docker { image 'maven:3.6.3-jdk-14' } }
            steps {
                sh 'mvn package'
            }
        }
        stage('DOCKER-BUILD') {
            steps {
                sh 'docker build -t covid-simulation-image .'
                sh 'docker run -d --name=simulation-container covid-simulation-image'
            }
        }
    }
}
