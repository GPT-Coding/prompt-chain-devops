## 1. Create Dockerfile for Java app using ChatGPT
1. Write a Dockerfile for the java application and expose port 8080
2. In the dockerfile, the jar package name of the java application can be passed in during build
3. Write a shell script for running java applications, which can set jvm related parameters
4. Use the shell script of the second step in the docker file
   In summary, generate a complete dockerfile and shell script

## 2. Create Kubernetes manifest file using ChatGPT
1. Create an executable and accessible k8s deployment manifest file for this docker image
2. Please add namespace tag and enter it as parameter along with application name and image name, here please use simple implementation
3. Add resource quotas to the deployment
4. Adjust deployment manifest with production and security best practices

## 3. Create CI/CD pipeline code using ChatGPT
1. please write a Jenkinsfile for the complete CI/CD pipeline for the above java application, including deployment to k8s cluster, use gradle to build java applications
2. please adjust the Jenkinsfile ,in  the final stage, apply deployment and service files to the k8s cluster and login cluster
3. add a stage to notify team through slack channel about the build status

### Note: Please check the docs directory in the project for the implementation process

