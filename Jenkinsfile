pipeline {
    // TODO set up raspberry pi as an agent (with jenkins and docker
    agent any

    // TODO install docker inside jenkins or run only unit tests (without testcontainers)
    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }

        }
        stage('Test') {
            steps {
               sh './gradlew test'
            }
        }
    }

    // TODO reporting is not working - the results can be seend only by accessing the files from jenkins
    post {
        always {
            junit 'build/reports/**/*.xml'
        }
    }
}