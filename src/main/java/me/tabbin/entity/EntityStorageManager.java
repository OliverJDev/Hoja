package me.tabbin.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import me.tabbin.HojaPluginI;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


public class EntityStorageManager implements EntityStorageManagerI{

    @Getter
    private LinkedList<Class<Entity>> entityTypesRegister = new LinkedList<>();
    @Getter
    private LinkedHashMap<Class, EntityStorage> entityStorages = new LinkedHashMap<>();

    private HojaPlugin instance;

    public EntityStorageManager(HojaPlugin plugin){
        instance = plugin;
    }


    public void addEntity(Entity entity){
        if (entity.getType() == null || entity.getType().equalsIgnoreCase("")) {
            entity.setType(entity.getClass().getSimpleName());
        }


        /*
        This essentially finds the class extneding the super class of entity
         */
        Class mainExtendingClass = entity.getClass();
        boolean isExtending = false;


        while(!isExtending){
            if (mainExtendingClass.getSuperclass() == null){
                return;
            }
            if(mainExtendingClass.getSuperclass().getSimpleName().equals("Entity")){
                isExtending = true;
            }else{
                mainExtendingClass = mainExtendingClass.getSuperclass();
            }
        }


        if(entityStorages.containsKey(mainExtendingClass)){
            if (!entityStorages.get(mainExtendingClass).getAll().containsKey(entity.getId())) {
                entityStorages.get(mainExtendingClass).addEntity(entity, entity.getId());
            }
        }else{
            entityStorages.put(mainExtendingClass, new EntityStorage(instance, mainExtendingClass));
            addEntity(entity);
        }

    }

    @Override
    public void syncAll() {
        for (Map.Entry<Class, EntityStorage> classEntityStorageEntry : getEntityStorages().entrySet()) {
            EntityStorage storage = classEntityStorageEntry.getValue();
            storage.getConfig().write(storage.getAll());
        }
    }

    @Override
    public void addEntityType(Class type) {
        getEntityTypesRegister().add(type);
    }
}
