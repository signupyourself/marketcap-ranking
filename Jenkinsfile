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
				sh './setup.sh'	    
	    		}
        	}
    	}
    	post {
		success{
	   		echo "Build successful"
        	}
        	failure{
           		echo "Build unsuccessful"
        	}
    	}

}
