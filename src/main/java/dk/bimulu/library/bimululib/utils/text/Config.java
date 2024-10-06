package dk.bimulu.library.bimululib.utils.text;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Config {
    private final File file;
    private final Logger logger;
    private final YamlConfiguration configuration;

    public Config(Plugin plugin, String path, String name) throws IOException, InvalidConfigurationException {
        if (path == null || name == null) {
            throw new IllegalArgumentException("Path and name cannot be null.");
        }
        this.file = new File(path, name.endsWith(".yml") ? name : (name + ".yml"));
        this.logger = plugin.getLogger();
        this.configuration = new YamlConfiguration();

        create();
        reload();
    }

    private boolean create() throws IOException {
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            return this.file.createNewFile();
        }
        return false;
    }

    private void reload() throws IOException, InvalidConfigurationException {
        try {
            configuration.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            this.logger.severe("Failed to reload YAML file: " + e.getMessage());
            throw e;
        }
    }

    private void save() throws IOException {
        try {
            configuration.save(this.file);
        } catch (IOException e) {
            this.logger.severe("Failed to save YAML file: " + e.getMessage());
            throw e;
        }
    }

    public boolean exists() {
        return file.exists();
    }


    public int getInt(String path) {
        return configuration.getInt(path);
    }

    public String getString(String path) {
        return configuration.getString(path);
    }

    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    public <T> T getObject(String path, Class<T> clazz) {
        return configuration.getObject(path, clazz);
    }
    public <T> T getObject(String path, Class<T> clazz, T defaultDef) {
        return configuration.getObject(path, clazz, defaultDef);
    }

    public Boolean getBoolean(String path) {
        return configuration.getBoolean(path);
    }

    public void set(String path, Object value) {
        configuration.set(path, value);
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
