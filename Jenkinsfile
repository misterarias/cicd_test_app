#!/usr/bin/env groovy
pipeline {
    stages {
        stage('Checkout code') {
            checkout scm
        }
        stage('Build & test') {
            sh "./mvnw test"
        }
    }
}
