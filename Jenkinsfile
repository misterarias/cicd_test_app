#!/usr/bin/env groovy
pipeline {
    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }
        stage('Build & test') {
            step {
                sh "./mvnw test"
            }
        }
    }
}
