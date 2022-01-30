pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew backend:test'
            }

        }
    }

    post {
        always {
            junit 'build/reports/**/*.xml'
        }
    }
}