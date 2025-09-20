pipeline {
    agent any

    tools {
        maven 'Maven-3.8'
        jdk 'JDK-11'
    }

    environment {
        RENDER_API_KEY = credentials('render-api-key')
        PROJECT_NAME = 'examen-devops'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/DIOUF-MOR/examen-devops.git'
            }
        }
/*
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        */

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Quality') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${PROJECT_NAME}:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Deploy to Render') {
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