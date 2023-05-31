pipeline {
  agent any
  stages {
    stage('Dev Build') {
      steps {
        sh '''sshagent([\'dev-ssh-credentials\']) {
 sh \'\'\'
 ssh -o StrictHostKeyChecking=no ubuntu@43.205.94.85 \'
 # Kill Java process
 sudo fuser -k 80/tcp
 # Pull latest code
 cd /home/ubuntu/jenkins-pipeline/leafhub
 git pull
 # Store commit ID
 export COMMIT_ID=$(git rev-parse HEAD)
 # Run maven spring boot
 nohup sudo mvn spring-boot:run > /dev/null 2>&1 &
 \'
 \'\'\'
}'''
        }
      }

    }
  }