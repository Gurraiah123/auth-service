pipeline {

    agent {
        label 'build-agent'
    }

    stages {

        stage('Verify Java') {
            steps {
                sh 'java -version'
                sh 'javac -version'
                sh 'mvn -version'
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Gurraiah123/auth-service.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {

                sshagent(['jenkins-agent-key']) {

                    sh '''
                        scp target/*.jar ubuntu@54.169.34.180:/home/ubuntu/app/

                        ssh ubuntu@54.169.34.180 "
                            pkill java || true
                            nohup java -jar /home/ubuntu/app/*.jar >/dev/null 2>&1 &
                        "
                    '''
                }
            }
        }
    }
}
