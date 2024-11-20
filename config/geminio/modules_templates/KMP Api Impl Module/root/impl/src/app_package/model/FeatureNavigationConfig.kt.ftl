package ${packageName}.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface ${__formattedModuleName}NavigationConfig {
    data object Main : ${__formattedModuleName}NavigationConfig
}