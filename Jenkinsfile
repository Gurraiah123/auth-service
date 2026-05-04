pipeline {

    agent {
        label 'build-agent'
    }

    stages {

        stage('Checkout') {

            steps {
                git branch: 'develop',
                url: 'YOUR_GITHUB_REPO'
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

                sshagent(['ec2-key']) {

                    sh '''

                    scp \
                    target/*.jar \
                    ubuntu@EC2_PUBLIC_IP:/home/ubuntu/app/

                    ssh ubuntu@EC2_PUBLIC_IP "

                    pkill java || true

                    nohup java \
                    -jar /home/ubuntu/app/*.jar &

                    "
                    '''
                }
            }
        }
    }
}
