package MicroserviceSandbox.buildTypes

import MicroserviceSandbox.vcsRoots.MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

object MicroserviceSandbox_Build : BuildType({
    uuid = "86a5392d-63ce-4be0-9f16-f32f89b04336"
    id = "MicroserviceSandbox_Build"
    name = "Build"
    artifactRules = "+:**/case/target/pitreports/** => pitreports"

    vcs {
        root(MicroserviceSandbox.vcsRoots.MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster)

    }

    steps {
        maven {
            name = "clean verify"
            goals = "clean verify"
            mavenVersion = defaultProvidedVersion()
            jdkHome = "%env.JDK_18_x64%"
            coverageEngine = jacoco {
                classLocations = """
                    +:**/target/classes/**
                    -:**/target/classes/**/*Application*
                """.trimIndent()
            }
        }
        maven {
            name = "deploy"
            goals = "install"
            mavenVersion = defaultProvidedVersion()
            jdkHome = "%env.JDK_18_x64%"
            runnerArgs = "-DskipTests"

        }
        maven {
            name = "Mutation Coverage"
            goals = "pitmp:run"
            mavenVersion = defaultProvidedVersion()
            jdkHome = "%env.JDK_18_x64%"
            workingDir = "case"
            pomLocation = "pom.xml"

        }
    }

    triggers {
        vcs {
        }
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster.id
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:5f460ae0-2380-4763-8bb9-7c6df62ad0e8"
                }
            }
            param("github_oauth_user", "McKratt")
        }
    }

})
