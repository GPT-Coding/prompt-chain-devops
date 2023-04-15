pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Checkout code from source control
                checkout scm

                // Use Gradle to build the Java application
                sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                // Use Gradle to run unit tests
                sh './gradlew test'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build a Docker image and tag it with the current Git commit hash
                sh "docker build -t myapp:${env.GIT_COMMIT} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                // Push the Docker image to a Docker registry (e.g. Docker Hub)
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                }
                sh "docker push myapp:${env.GIT_COMMIT}"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Deploy the Docker image to a Kubernetes cluster
                withCredentials([kubeconfigFile(credentialsId: 'kubeconfig-credentials', variable: 'KUBECONFIG_FILE')]) {
                    sh "export KUBECONFIG=$KUBECONFIG_FILE"
                }
                sh "kubectl apply -f k8s/deployment.yaml"
                sh "kubectl apply -f k8s/service.yaml"
            }
        }

        stage('Notify Slack Channel') {
            steps {
                // Notify the team through a Slack channel about the build status
                withCredentials([string(credentialsId: 'slack-webhook-url', variable: 'SLACK_WEBHOOK_URL')]) {
                    slackSend(
                        color: env.BUILD_STATUS == 'SUCCESS' ? 'good' : 'danger',
                        message: "Build ${env.BUILD_STATUS} for ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)",
                        webhookUrl: SLACK_WEBHOOK_URL
                    )
                }
            }
        }
    }
}
