pipeline {
    agent any
    stages {
        stage('SCM') {
            steps {
                git url: 'git@GitLab:devops/HelloWorld.git', branch: 'development', credentialsId: '10cf7fb0-e88a-4d36-9a91-bdb2e4af7652'
            }
        }
        stage('build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh './gradlew --info sonarqube'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    // Requires SonarQube Scanner for Jenkins 2.7+
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}