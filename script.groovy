def Docker_Registry_Login(){
    echo "Logging Into The Nexus Docker Repo"
    withCredentials([usernamePassword(credentialsId: 'spanartifacts', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
    sh "echo \"${NEXUS_PASS}\" | docker login --username \"${NEXUS_USER}\" --password-stdin ${NEXUS_URL}"          
    }
}


def Push_To_Registry(){
    echo "Pushing The Docker Images To Nexus Regsitry"
    sh "docker tag ${Application.toLowerCase()}:${tags} ${NEXUS_URL}/${Application.toLowerCase()}:${tags}"
    sh "docker push ${NEXUS_URL}/${Application.toLowerCase()}:${tags}" 
}

return this

