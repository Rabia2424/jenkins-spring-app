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
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t myapp .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'echo %DOCKER_PASS% > pass.txt'
                    bat 'docker login -u %DOCKER_USER% --password-stdin < pass.txt'
                    bat 'docker tag myapp %DOCKER_USER%/myapp:latest'
                    bat 'docker push %DOCKER_USER%/myapp:latest'
                    bat 'del pass.txt'
                }
            }
        }

        stage('Deploy to K8s') {
            steps {
                    withEnv(['KUBECONFIG=C:\\Users\\Rabia\\.kube\\config']) {
                        bat 'kubectl apply -f k8s\\deployment.yaml --validate=false'
                        bat 'kubectl apply -f k8s\\service.yaml --validate=false'
                    }
            }
        }
    }
}
