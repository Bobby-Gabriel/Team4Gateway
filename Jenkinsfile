pipeline {
    agent any

    stages {
        stage('Pull From Github'){
            steps{
                git branch: 'develop', url: "https://github.com/Bobby-Gabriel/Team4Gateway.git"
            }
        }
        stage('Docker Build/Package'){
            steps{
                sh '''docker build -t bobbygabriel3510/team4project-gateway:latest .'''
            }
        }
        stage('Approval Step'){
            steps{
                script{
                   env.APPROVED_DEPLOY = input message: 'User input required',
                   parameters: [choice(name: 'Deploy?', choices: 'no\nyes', description: 'Choose "yes" if you want to deploy this build')]
                }
            }
        }
        stage('Docker push'){
            steps{
                
                sh '''docker login --username bobbygabriel3510 --password dckr_pat_tDIgEtXzVanNxGzsaQFg3oADCC0'''
                sh '''docker push bobbygabriel3510/team4project-gateway:latest'''
            }
        }
    }
}        
        
    
