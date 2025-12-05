def call(Map config = [:]) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Build') {
                steps {
                    sh "${config.buildCmd ?: 'npm run build'}"
                }
            }

            stage('Test') {
                steps {
                    sh "${config.testCmd ?: 'npm test'}"
                }
            }
        }

        post {
            success {
                echo "Pipeline completed successfully!"
            }
            failure {
                echo "Pipeline failed!"
            }
        }
    }
}
