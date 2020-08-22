package work.gavenda.quik

import org.bukkit.plugin.java.JavaPlugin

/**
 * Your plugin entry point.
 */
class Plugin : JavaPlugin() {

    override fun onEnable() {
        // Load configuration
        saveDefaultConfig()
        loadConfig()
    }

    override fun onDisable() {
    }

    private fun loadConfig() {
        Config.load(config)
    }
}
