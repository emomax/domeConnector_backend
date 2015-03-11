package myExtPackage;

// SFS-items
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

// Custom classes
import GameObjects.World;

//! MainExtension.java handles broadcasting of ingame logics.
public class MainExtension extends SFSExtension {

    private World world;
    
    //! The init function adds the requesthandlers for our different broadcasted items.
    @Override
    public void init() {
        world = new World(this);
        
        addRequestHandler("TransformShip", SendTransformHandler.class);
    }
}