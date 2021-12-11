package me.tabbin.entity.adapters;

import com.google.gson.*;
import me.tabbin.HojaPlugin;
import me.tabbin.entity.Entity;
import me.tabbin.entity.EntityStorageManager;
import org.bukkit.Location;

import java.lang.reflect.Type;

public class EntityAdapter implements JsonDeserializer<Entity>, JsonSerializer<Entity> {

    private HojaPlugin instance;
    Class t;
    public EntityAdapter(HojaPlugin instance, Class t){
        this.instance = instance;
        this.t = t;
    }

    public EntityAdapter(){

    }
    @Override
    public Entity deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if ( !json.isJsonObject() ) {
            throw new JsonParseException( "not a JSON object" );
        }
        final JsonObject obj = (JsonObject) json;
        final JsonElement x = obj.get( "id" );
        return instance.getEntityStorageManager().getEntityStorages().get(type.getClass()).get(x.getAsString());
    }

    @Override
    public JsonElement serialize(Entity entity, Type type, JsonSerializationContext jsonSerializationContext) {
        if(type == entity.getClass()){

            RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Entity.class, "type");
            for (Class<Entity> entityClass : instance.getEntityStorageManager().getEntityTypesRegister()) {
                runtimeTypeAdapterFactory = runtimeTypeAdapterFactory.registerSubtype(entityClass, entityClass.getSimpleName());
            }
            return new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(Location.class, new LocationAdapter())
                    //
                    //.registerTypeAdapter(type, new EntityAdapter(instance, entity.getClass()))
                    //.registerTypeAdapter(HGUIDesign.class, new HGUIDesignAdapter())
                    .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                    .create().toJsonTree(entity);
        }else{
            throw new JsonParseException("" +  type + " v " +  entity.getClass());
        }
        //final JsonObject obj = new JsonObject();
       // obj.addProperty( "id", entity.getId() );
       // return obj;
    }
}
