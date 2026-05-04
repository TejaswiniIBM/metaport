node('linux')
{
   
  stage ('Poll') {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: "https://github.com/TejaswiniIBM/metaport.git"]]])
        }
  stage('Build') {
    build job: 'Port-Build-Test', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/TejaswiniIBM/metaport.git'), string(name: 'PORT_DESCRIPTION', value: 'A set of utilities for z/OS Open Tools' )]
  }
   stage('Publish Test') {
    // Add your publish script testing here
    // Example:
    // sh './scripts/test-publish.sh'
    // or
    // build job: 'Port-Publish-Test', parameters: [...]
      build job: 'Port-Publish-Test', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/TejaswiniIBM/metaport.git'), string(name: 'PORT_DESCRIPTION', value: 'A set of utilities for z/OS Open Tools' )]
  }
}
