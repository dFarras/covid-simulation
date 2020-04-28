def image_name = "simulation-image"
def container_name = "simulation-cntr"
pipeline {
    agent any
    stages {
        stage('PACKAGE') {
            agent { 
                docker { 
                    image 'maven:3.6.3-jdk-14' 
                    reuseNode true
                } 
            }
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
                sh "docker build -t ${image_name} ."
                sh "docker run -d -p 8000:8080 --name=${container_name} ${image_name}"
            }
        }
    }
}
