package work.gavenda.quik

import org.bukkit.configuration.file.*

/**
 * Configuration for the plugin.
 */
object Config {
    /**
     * Spigot plugin file configuration, should be possible to reassign. We want /reload to be smooth as possible.
     */
    private lateinit var config: FileConfiguration

    object Database {
        val File = config.getString("db.file", "quik.db")!! // !! added since it has a default, weirdly marked @Nullable
    }

    /**
     * Load plugin configuration.
     */
    fun load(configuration: FileConfiguration) {
        config = configuration
    }

}