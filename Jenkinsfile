pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh 'mvn -T 4 clean compile'
      }
    }

    stage('Create folders') {
      steps {
        sh 'mkdir -p res/database'
        sh 'rm res/database/*.db'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }

  }
}