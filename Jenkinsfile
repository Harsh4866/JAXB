pipeline {
    agent any

    triggers {
        githubPush() // triggers on push / PR
    }

    environment {
        GITHUB_CREDENTIALS = 'github-token' // Jenkins credentials ID for GitHub PAT
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                git branch: 'main', 
                    url: 'https://github.com/Harsh4866/JAXB.git', 
                    credentialsId: "${GITHUB_CREDENTIALS}"
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project using Maven...'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Add your deployment commands here, for example:
                // sh 'cp target/JAXB.jar /path/to/deploy/'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
            githubNotify context: 'CI/CD Pipeline', status: 'SUCCESS'
        }
        failure {
            echo 'Pipeline failed!'
            githubNotify context: 'CI/CD Pipeline', status: 'FAILURE'
        }
    }
}
