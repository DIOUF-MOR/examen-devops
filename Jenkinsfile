pipeline {
    agent none

    environment {
        RENDER_API_KEY = credentials('render-api-key')
        PROJECT_NAME = 'examen-devops'
    }

    stages {
        stage('Checkout') {
            agent any
            steps {
                git branch: 'main', url: 'https://github.com/DIOUF-MOR/examen-devops.git'
            }
        }

        stage('Build') {
            agent {
                docker {
                    image 'maven:3.8-openjdk-11'
                    args '-v $HOME/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            agent any
            steps {
                script {
                    sh 'docker build -t ${PROJECT_NAME}:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Deploy to Render') {
            agent any
            steps {
                script {
                    sh '''
                        curl -X POST https://api.render.com/v1/services/usr-d1vu4fqdbo4c73fsodt0/deploys \
                        -H "Authorization: Bearer ${RENDER_API_KEY}" \
                        -H "Content-Type: application/json"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline exécuté avec succès!'
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
    }
}
