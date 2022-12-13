pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'git@github.com:TERMALIA/ETEREA.api.rest.git'

                // Run Maven on a Unix agent.
                sh "mvn clean package"

                // Build image
                sh "docker build -t dqmdz/eterea.api:1.0.0:${BUILD_NUMBER}"
            }

        }
    }
}
