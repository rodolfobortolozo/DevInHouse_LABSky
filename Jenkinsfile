pipeline{
    agent any

    tools{
        maven 'maven_395'
        jdk 'JAVA17'
    }

    stages{
        stage("Verificar Ferramentas"){
            steps{
                sh "mvn --version"
                sh "java -version"
            }
            
        }
        stage("Checkout Código"){
            steps{
                git branch: 'main',
                url: "https://github.com/rodolfobortolozo/Modulo3-Projeto-Avaliativo-1.git"
            }
        }
        
        stage("Code coverage") {
           steps {
               jacoco(
                    execPattern: '**/target/**.exec',
                    classPattern: '**/target/classes',
                    sourcePattern: '**/src',
                    inclusionPattern: 'com/iamvickyav/**',
                    changeBuildStatus: true,
                    minimumInstructionCoverage: '30',
                    maximumInstructionCoverage: '80')
           }
        }
        
        stage('OWASP Dependency-Check Vunerabilidade') {
            steps {
                dependencyCheck additionalArguments: '''
                -o './'
                -s './'
                -f 'ALL' 
                --prettyPrint''', odcInstallation: 'dependency_check8.4.3'
                dependencyCheckPublisher pattern: 'dependency-check-report.xml'
                
            }
        }
        
        stage("Qualidade do Código"){
            steps{
                sh "mvn clean verify sonar:sonar -Dsonar.projectKey=Sky_lab -Dsonar.host.url=http://172.18.0.2:9000 -Dsonar.login=sqa_ecf516e91318f582b3c58ea3fb46c089dbddaebd"
            }
        }
        
        stage("Build da Aplicação"){
            steps{
                sh "mvn package"
            }
        }
    }

}