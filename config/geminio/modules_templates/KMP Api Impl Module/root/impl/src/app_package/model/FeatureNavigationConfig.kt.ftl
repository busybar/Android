package ${packageName}.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface ${__formattedModuleName}NavigationConfig {
    @Serializable
    data object Main : ${__formattedModuleName}NavigationConfig
}