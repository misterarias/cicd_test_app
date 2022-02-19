#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }
        stage('Build & test') {
            steps {
                sh "./mvnw test"
            }
        }
    }
}
