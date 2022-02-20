package me.temez.moonrise.skypvp.config;

import me.temez.moonrise.skypvp.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SConfig extends FileConfiguration{

    private String path;
    private Main instance;
    File file;
    private FileConfiguration config;


    public SConfig(String path){
        this.instance = Main.getInstance();
        this.path = path;
        this.file = new File(instance.getDataFolder(), path);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            instance.saveResource(path, false);
        }

        config = new YamlConfiguration();

        try{
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(){
        try {
            config.save(new File(instance.getDataFolder(), path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(){
        return this.config;
    }

    @Override
    public String saveToString() {
        return null;
    }
    @Override
    public void loadFromString(String contents) {

    }
}
