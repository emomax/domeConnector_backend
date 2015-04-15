package myExtPackage;

// SFS-items
import com.smartfoxserver.v2.extensions.SFSExtension;

// Custom classes
import GameObjects.World;

//! MainExtension.java handles broadcasting of ingame logics.
public class MainExtension extends SFSExtension {

    private World world;
    
    public static boolean pilotSelected = false;
    
    //! The init function adds the requesthandlers for our different broadcasted items.
    @Override
    public void init() {
        // Used for handling all objects and their functions.
        world = new World(this);
        
        addRequestHandler("RequestTransform", SendTransformHandler.class);
        addRequestHandler("Fire", FireHandler.class);
        addRequestHandler("EngineerUpdate", EngineerHandler.class);
    }
    
    public World getWorld() { return world; }
}