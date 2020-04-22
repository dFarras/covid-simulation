pipeline {
    def image_name = "covid-simulation-image"
    def container_name = "simulation-container"
    agent { docker { image 'maven:3.6.3-jdk-14' } }
    stages {
        stage('DOWNLOAD') {
            steps {
                checkout scm
            }
        }
        stage('PACKAGE') {
            steps {
                sh 'mvn package'
            }
        }
        stage('KILL-PREV') {
            steps {
                try {
                sh "docker stop ${container_name}"
                sh "docker rm ${container_name}"
                sh "docker rmi ${image_name}"
                } catch(Exception e) {
                    echo e.getMessage()
                }
            }
        }
        stage('DOCKER-BUILD') {
            steps {
                sh "docker build -t ${image_name} ."
                sh "docker run -d -p 80:8080 --name=${container_name} ${image_name}"
            }
        }
    }
}
