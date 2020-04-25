def image_name = "covid-simulation-image"
def container_name = "simulation-container"
pipeline {
    agent any
    stages {
        stage('PACKAGE') {
            agent { docker { image 'maven:3.6.3-jdk-14' } }
            steps {
                sh 'mvn package'
            }
        }
        stage('KILL-PREV') {
            steps {
                sh "docker stop ${container_name} || true"
                sh "docker rm ${container_name} || true"
                sh "docker rmi ${image_name} || true"
            }
        }
        stage('DOCKER-BUILD') {
            steps {
                sh "pwd"
                echo "${workspace}"
                sh "docker build -t ${image_name} ."
                sh "docker run -d -p 80:8080 --name=${container_name} ${image_name}"
            }
        }
    }
}
