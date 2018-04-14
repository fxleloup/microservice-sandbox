package MicroserviceSandbox.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

object MicroserviceSandbox_Build : BuildType({
    uuid = "86a5392d-63ce-4be0-9f16-f32f89b04336"
    id = "MicroserviceSandbox_Build"
    name = "Build"

    vcs {
        root(MicroserviceSandbox.vcsRoots.MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster)

    }

    steps {
        maven {
            name = "clean verify"
            goals = "clean verify"
            mavenVersion = defaultProvidedVersion()
            jdkHome = "%env.JDK_18_x64%"
        }
    }

    triggers {
        vcs {
        }
    }
})
