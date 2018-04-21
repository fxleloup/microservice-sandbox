package MicroserviceSandbox.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster : GitVcsRoot({
    uuid = "e30321e5-6aeb-4e0b-8ebb-60ecc2de0010"
    id = "MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster"
    name = "https://github.com/McKratt/microservice-sandbox#refs/heads/master"
    url = "https://github.com/McKratt/microservice-sandbox"
    authMethod = password {
        userName = "McKratt"
        password = "credentialsJSON:5f460ae0-2380-4763-8bb9-7c6df62ad0e8"
    }
})
