pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo "Starting build"
                sh './gradlew build'
            }
            }
		stage('deploy'){
            steps{
                sh './deploy.sh'
            }
        }
    }
    post {
        always(){
            cleanWs()
        }
        success{
            echo "Build successful"
        }
        failure{
            echo "Build unsuccessful"
        }
    }

}
