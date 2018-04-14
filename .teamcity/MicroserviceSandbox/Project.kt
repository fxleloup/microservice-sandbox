package MicroserviceSandbox

import MicroserviceSandbox.buildTypes.*
import MicroserviceSandbox.vcsRoots.*
import MicroserviceSandbox.vcsRoots.MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "4c256d8d-50e3-4e11-bb36-ad7074c9506a"
    id = "MicroserviceSandbox"
    parentId = "_Root"
    name = "Microservice Sandbox"

    vcsRoot(MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster)

    buildType(MicroserviceSandbox_Build)

    features {
        versionedSettings {
            id = "PROJECT_EXT_5"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = MicroserviceSandbox_HttpsGithubComMcKrattMicroserviceSandboxRefsHeadsMaster.id
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
