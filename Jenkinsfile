#!/usr/bin/env groovy

DOCKER_IMAGE_NAME = "juanito/spring-boot-test"
pipeline {
    agent any
    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }
        stage('Run tests') {
            steps {
                sh "./mvnw test"
            }
        }
        stage('Build package') {
            steps {
                sh "./mvnw package -DskipTests=true"
            }
        }
        stage('Build Image') {
            steps {
                sh "docker build --tag image ."
            }
        }
        stage('Push Image') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USR',
                        passwordVariable: 'DOCKER_PSW)
                ]) {
                    sh """
                    docker login -u ${DOCKER_USR} -p ${DOCKER_PSW}
                    docker tag image ${DOCKER_IMAGE_NAME}:latest
                    docker push ${DOCKER_IMAGE_NAME}:latest
                    """.trim()
                }
            }
        }
    }
}
