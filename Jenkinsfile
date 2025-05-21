pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/Rabia2424/jenkins-spring-app'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
                        docker tag myapp \$DOCKER_USER/myapp:latest
                        docker push \$DOCKER_USER/myapp:latest --quiet
                    """
                }
            }
        }

        stage('Deploy to K8s') {
            steps {
                sh 'minikube kubectl -- apply -f k8s/deployment.yaml'
                sh 'minikube kubectl -- apply -f k8s/service.yaml'
            }
        }
    }
}
