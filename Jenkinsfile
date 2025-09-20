pipeline {
    agent any

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

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

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

        // ... reste des stages
    }
}