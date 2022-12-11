pipeline {
    agent any
    tools { 
        M3 'Maven 3.8.6' 
    }
    stages {
        stage('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M3_HOME = ${M3_HOME}"
                '''
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}